package indi.uhyils.rpc.netty.callback.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.content.RpcHeaderContext;
import indi.uhyils.rpc.enums.RpcResponseTypeEnum;
import indi.uhyils.rpc.enums.RpcStatusEnum;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcBeanNotFoundException;
import indi.uhyils.rpc.exception.RpcBusinessException;
import indi.uhyils.rpc.exception.RpcSpiInitException;
import indi.uhyils.rpc.exception.RpcVersionNotSupportedException;
import indi.uhyils.rpc.exchange.content.MyRpcContent;
import indi.uhyils.rpc.exchange.pojo.content.RpcContent;
import indi.uhyils.rpc.exchange.pojo.content.RpcRequestContent;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.data.factory.NormalRpcResponseFactory;
import indi.uhyils.rpc.exchange.pojo.data.factory.RpcFactory;
import indi.uhyils.rpc.exchange.pojo.data.factory.RpcFactoryProducer;
import indi.uhyils.rpc.exchange.pojo.head.RpcHeader;
import indi.uhyils.rpc.netty.callback.MethodInvoker;
import indi.uhyils.rpc.netty.callback.MethodInvokerFactory;
import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.pojo.InvokeResult;
import indi.uhyils.rpc.netty.spi.step.RpcStep;
import indi.uhyils.rpc.netty.spi.step.template.ProviderResponseObjExtension;
import indi.uhyils.rpc.spi.RpcSpiManager;
import indi.uhyils.rpc.util.RpcObjectTransUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * 这个只是一个样例,具体如何执行要看下一级
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月23日 19时15分
 */
@RpcSpi
public class RpcDefaultRequestCallBack implements RpcCallBack {

    /**
     * Rpc的bean们
     */
    private final Map<Class<?>, Object> beans = new ConcurrentHashMap<>();

    /**
     * 返回值拦截器
     */
    private List<ProviderResponseObjExtension> providerResponseObjExtensions;

    /**
     * 方法执行器
     */
    private MethodInvoker methodInvoker;

    public RpcDefaultRequestCallBack(Map<String, Object> beans) throws ClassNotFoundException {
        for (Map.Entry<String, Object> entity : beans.entrySet()) {
            String beanName = entity.getKey();
            Object bean = entity.getValue();
            Class<?> key = Class.forName(beanName);
            this.beans.put(key, bean);
        }
    }

    public RpcDefaultRequestCallBack() throws InterruptedException {
        this.providerResponseObjExtensions = RpcSpiManager.createOrGetExtensionListByClassNoInit(RpcStep.class, ProviderResponseObjExtension.class);
        this.methodInvoker = MethodInvokerFactory.createMethodInvoker();
    }


    @Override
    public void init(Object... params) {
        Map<String, Object> param = (Map<String, Object>) params[0];
        try {
            for (Map.Entry<String, Object> entity : param.entrySet()) {
                String beanName = entity.getKey();
                Object bean = entity.getValue();
                Class<?> key = Class.forName(beanName);
                this.beans.put(key, bean);
            }
        } catch (ClassNotFoundException e) {
            throw new RpcSpiInitException(e, this.getClass());
        }
    }

    @Override
    public RpcData createRpcData(byte[] data) throws InterruptedException {
        /*解析*/
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.REQUEST);
        // 获取到的Request
        assert build != null;

        return build.createByBytes(data);
    }

    @Override
    public RpcContent getContent(byte[] data) throws InterruptedException {
        RpcData request = createRpcData(data);
        Integer version = request.rpcVersion();

        if (version > MyRpcContent.VERSION) {
            throw new RpcVersionNotSupportedException(version, MyRpcContent.VERSION);
        }
        return request.content();

    }

    @Override
    public InvokeResult invoke(RpcContent content) {
        if (content instanceof RpcRequestContent) {
            RpcRequestContent requestContent = (RpcRequestContent) content;
            // 执行方法
            return execute(requestContent);
        }
        return null;
    }

    @Override
    public RpcData assembly(Long unique, InvokeResult result) throws InterruptedException {
        Map<String, String> headerMap = RpcHeaderContext.get();
        List<RpcHeader> headers = new ArrayList<>(headerMap.size() + 1);
        headerMap.entrySet().forEach(t -> headers.add(new RpcHeader(t.getKey(), t.getValue())));
        RpcHeader[] rpcHeaders = headers.toArray(new RpcHeader[0]);

        String responseType;
        RpcStatusEnum rpcStatus = RpcStatusEnum.OK;
        String resultJson = result.getResultJson();
        if (!result.getSuccess()) {
            return new NormalRpcResponseFactory().createErrorResponse(unique, result.getThrowable(), rpcHeaders);
        }

        if (StringUtils.isEmpty(resultJson)) {
            responseType = RpcResponseTypeEnum.NULL_BACK.getCode().toString();
        } else {
            responseType = RpcResponseTypeEnum.STRING_BACK.getCode().toString();
        }
        return Objects.requireNonNull(RpcFactoryProducer.build(RpcTypeEnum.RESPONSE)).createByInfo(unique, new Object[]{rpcStatus.getCode()}, rpcHeaders, responseType, resultJson);
    }

    /**
     * 执行方法
     *
     * @param requestContent
     *
     * @return
     */
    private InvokeResult execute(RpcRequestContent requestContent) {
        try {
            Class<?> clazz = Class.forName(requestContent.getServiceName());
            Object target = null;
            for (Map.Entry<Class<?>, Object> entry : beans.entrySet()) {
                if (clazz.isAssignableFrom(entry.getKey())) {
                    target = entry.getValue();
                    break;
                }
            }

            if (target == null) {
                throw new RpcBeanNotFoundException(clazz);
            }
            clazz = target.getClass();
            String[] methodParameterTypes = requestContent.getMethodParameterTypes();
            Class[] methodClass = new Class[methodParameterTypes.length];
            for (int i = 0; i < methodParameterTypes.length; i++) {
                methodClass[i] = Class.forName(methodParameterTypes[i]);
            }

            Method[] declaredMethods = clazz.getDeclaredMethods();
            List<Method> nameEqualMethods = Arrays.stream(declaredMethods).filter(t -> t.getName().equals(requestContent.getMethodName())).collect(Collectors.toList());
            Method declaredMethod = null;
            for (Method nameEqualMethod : nameEqualMethods) {
                if (nameEqualMethod.getParameterCount() == methodClass.length) {
                    Class<?>[] parameterTypes = nameEqualMethod.getParameterTypes();
                    boolean success = true;
                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (!parameterTypes[i].isAssignableFrom(methodClass[i])) {
                            success = false;
                            break;
                        }
                    }
                    if (success) {
                        declaredMethod = nameEqualMethod;
                        break;
                    }
                }
            }

            Object[] args = requestContent.getArgs();
            args = RpcObjectTransUtil.changeObjRequestParadigm(args, clazz, declaredMethod, target);

            Object invoke = methodInvoker.doMethodInvoke(target, declaredMethod, args);
            for (ProviderResponseObjExtension providerResponseObjExtension : providerResponseObjExtensions) {
                invoke = providerResponseObjExtension.doFilter(invoke, requestContent);
            }
            String resultJson = invoke == null ? "" : JSON.toJSONString(invoke, SerializerFeature.WriteClassName);
            return InvokeResult.build(resultJson, declaredMethod.getReturnType(), declaredMethod.getGenericReturnType());
        } catch (InvocationTargetException e) {
            throw new RpcBusinessException(e.getTargetException());
        } catch (Throwable e) {
            throw new RpcBusinessException(e);
        }
    }

    /**
     * 获取父类的泛型所代表的真实类
     *
     * @param clazz
     *
     * @return
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Map<String, String> getSuperClassTypeTransMap(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ParameterizedType genericSuperclass = (ParameterizedType) (clazz.getGenericSuperclass());
        Class rawType = (Class) genericSuperclass.getRawType();
        TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Map<String, String> typeTransMap = new ConcurrentHashMap<>(typeParameters.length);
        for (int i = 0; i < typeParameters.length; i++) {
            String name = typeParameters[i].getName();
            String typeName = actualTypeArguments[i].getTypeName();
            typeTransMap.put(name, typeName);
        }
        return typeTransMap;
    }


}
