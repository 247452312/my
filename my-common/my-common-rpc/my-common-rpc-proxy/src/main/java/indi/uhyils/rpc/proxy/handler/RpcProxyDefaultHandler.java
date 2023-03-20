package indi.uhyils.rpc.proxy.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.config.ConsumerConfig;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.content.RpcHeaderContext;
import indi.uhyils.rpc.enums.RpcResponseTypeEnum;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.MyRpcProviderThrowException;
import indi.uhyils.rpc.exchange.pojo.content.RpcResponseContent;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.data.factory.RpcFactory;
import indi.uhyils.rpc.exchange.pojo.data.factory.RpcFactoryProducer;
import indi.uhyils.rpc.exchange.pojo.head.RpcHeader;
import indi.uhyils.rpc.factory.RpcParamExceptionFactory;
import indi.uhyils.rpc.netty.spi.step.RpcStep;
import indi.uhyils.rpc.netty.spi.step.template.ConsumerResponseObjectExtension;
import indi.uhyils.rpc.proxy.exception.MethodAndArgsNoMatchException;
import indi.uhyils.rpc.registry.Registry;
import indi.uhyils.rpc.registry.RegistryFactory;
import indi.uhyils.rpc.spi.RpcSpiManager;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.LogUtil;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * 默认的rpc代理类,自己实现代理见{@link RpcProxyHandlerInterface}
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月28日 06时47分
 * @see RpcProxyHandlerInterface
 */
@RpcSpi(single = false)
public class RpcProxyDefaultHandler implements RpcProxyHandlerInterface {

    private static final String TO_STRING = "toString";


    /**
     * 这个handler代理的类
     */
    private Class<?> type;

    /**
     * 注册类
     */
    private Registry<?> registry;

    /**
     * 消费者接收回复Object拦截器
     */
    private List<ConsumerResponseObjectExtension> consumerResponseObjectExtensions;

    /**
     * 唯一id生成器(雪花)
     */
    private IdUtil idUtil;

    /**
     * 创建时初始化
     *
     * @param clazz
     */
    public RpcProxyDefaultHandler(Class<?> clazz) {
        init(clazz);
    }

    @Override
    public void init(Object... params) {
        if (params == null || !(params[0] instanceof Class)) {
            throw RpcParamExceptionFactory.newException("<RpcProxyDefaultHandler>参数不正确,应该为%s,实际为:%s", "java.lang.Class[0]", JSON.toJSONString(params));
        }
        Class<?> clazz = (Class<?>) params[0];
        this.type = clazz;
        this.idUtil = new IdUtil();
        // 如果懒加载,那么就不加载
        if (isCheck()) {
            initRegistry(clazz);
        }
        consumerResponseObjectExtensions = RpcSpiManager.createOrGetExtensionListByClassNoInit(RpcStep.class, ConsumerResponseObjectExtension.class);
    }

    /**
     * 配置中是否使用了懒加载
     *
     * @return
     */
    private boolean isCheck() {
        RpcConfig instance = RpcConfigFactory.getInstance();
        ConsumerConfig consumer = instance.getConsumer();
        return consumer.getCheck();
    }

    private void initRegistry(Class<?> clazz) {
        try {
            this.registry = RegistryFactory.createConsumer(clazz);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
    }

    /**
     * 创建时不初始化 兼容spi
     */
    public RpcProxyDefaultHandler() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InterruptedException {
        // 防止proxy自动调用toString方法导致的报错
        if (TO_STRING.equals(method.getName())) {
            return "this is the MY_RPC interface,it`s name is " + proxy.getClass().getSimpleName();
        }

        // 懒加载时使用
        if (registry == null) {
            initRegistry(type);
        }

        // 验证method和arg是否正确
        validateArgsWithMethodParams(args, method);
        // 初始化RPCData
        RpcData rpcData = initRpcData(idUtil.newId(), method.getName(), Arrays.stream(args).map(Object::getClass).toArray(Class[]::new), args);
        // registry执行方法
        RpcData invoke = registry.invoke(rpcData);
        // 解析结果 - 正常返回或者报错
        return parseResult(method, invoke);

    }

    /**
     * 验证method的入参和args是否相同
     *
     * @param args   入参
     * @param method 方法本身
     */
    private void validateArgsWithMethodParams(Object[] args, Method method) throws MethodAndArgsNoMatchException {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (args.length != parameterTypes.length) {
            throw new MethodAndArgsNoMatchException(args.length, method);
        }

        for (int i = 0; i < args.length; i++) {
            if (!parameterTypes[i].isAssignableFrom(args[i].getClass())) {
                throw new MethodAndArgsNoMatchException(args[i], parameterTypes[i], method, i);
            }
        }

    }

    /**
     * 初始化rpcData
     *
     * @param unique
     * @param methodName
     * @param paramType
     * @param args
     *
     * @return
     */
    private RpcData initRpcData(Long unique, String methodName, Class[] paramType, Object[] args) throws InterruptedException {
        Map<String, String> headers = RpcHeaderContext.get();
        if (headers == null) {
            headers = new HashMap<>();
            headers.put("default_value", "value");
        }
        return initRpcData(unique, methodName, headers, paramType, args);
    }

    /**
     * 解析结果 - 正常返回或者报错
     *
     * @param method
     * @param invoke
     *
     * @return
     */
    @Nullable
    private Object parseResult(Method method, RpcData invoke) {
        RpcResponseContent content = (RpcResponseContent) invoke.content();

        final RpcResponseTypeEnum parse = RpcResponseTypeEnum.parse(content.responseType());
        switch (parse) {
            case EXCEPTION:
                throw new MyRpcProviderThrowException((Throwable) JSONObject.parse(content.getResponseContent()));
            case STRING_BACK:
                //后置自定义扩展处理返回
                return postProcessing(invoke, JSON.parse(content.getResponseContent(), Feature.SupportAutoType));
            case NULL_BACK:
                return null;
            default:
                throw new RuntimeException("未知的返回值类型");
        }
    }

    /**
     * 初始化rpcData
     *
     * @param unique
     * @param methodName
     * @param headers
     * @param paramType
     * @param args
     *
     * @return
     */
    private RpcData initRpcData(Long unique, String methodName, Map<String, String> headers, Class[] paramType, Object[] args) throws InterruptedException {
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.REQUEST);

        RpcHeader[] rpcHeaders = headers.entrySet().stream().map(t -> new RpcHeader(t.getKey(), t.getValue())).toArray(RpcHeader[]::new);

        // 类型的返回值
        String paramTypeStr = parseParamTypeToStr(paramType);

        return build.createByInfo(unique, null, rpcHeaders, type.getName(), "1", methodName, paramTypeStr, JSON.toJSONString(args, SerializerFeature.WriteClassName), "[]");
    }

    /**
     * spi加载的类进行后置处理
     *
     * @param rpcData
     * @param result
     *
     * @return
     */
    private Object postProcessing(RpcData rpcData, Object result) {
        for (ConsumerResponseObjectExtension consumerResponseObjectExtension : consumerResponseObjectExtensions) {
            result = consumerResponseObjectExtension.doFilter(result, rpcData);
        }
        return result;
    }

    /**
     * 获取paramType的字符串
     *
     * @param paramType
     *
     * @return
     */
    private String parseParamTypeToStr(Class[] paramType) {
        StringBuilder sb = new StringBuilder();
        for (Class<?> paramTypeClass : paramType) {
            sb.append(paramTypeClass.getName());
            sb.append(";");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
