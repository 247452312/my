package indi.uhyils.rpc.spring.util;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.proxy.generic.GenericService;
import indi.uhyils.rpc.spring.RpcConsumerBeanFieldInjectConfiguration;
import indi.uhyils.rpc.util.RpcObjectTransUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * rpc 泛化接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月24日 17时29分
 */
public class RpcApiUtil {


    /**
     * 接口名称包分隔符
     */
    public static final String INTERFACE_NAME_PACKAGE_SEPARATOR = ".";


    /**
     * 默认的协议
     */
    private static final String DEFAULT_PROCOTOL = "dubbo";

    /**
     * ReferenceConfig缓存(重量级, 不缓存太慢了, 但是还没有考虑微服务过多的情况)
     */
    private static final HashMap<String, GenericService> MAP = new HashMap<>();

    /**
     * rpc泛化接口调用类
     *
     * @param interfaceName 接口的名字,可以用全名或者接口名
     * @param methodName    方法名
     * @param args          方法参数
     * @param request       请求
     * @return 方法返回值
     */
    public static ServiceResult rpcApiTool(String interfaceName, String methodName, List<Object> args, DefaultRequest request) {
        return getServiceResult(interfaceName, methodName, args, request, Boolean.FALSE, DEFAULT_PROCOTOL);
    }

    /**
     * rpc泛化接口调用类
     *
     * @param interfaceName 接口的名字,可以用全名或者接口名
     * @param methodName    方法名
     * @param args          方法参数
     * @return 方法返回值
     */
    public static ServiceResult rpcApiTool(String interfaceName, String methodName, Object... args) {
        return getServiceResult(interfaceName, methodName, Arrays.asList(args), new DefaultRequest(), Boolean.FALSE, DEFAULT_PROCOTOL);
    }

    /**
     * rpc泛化接口调用类(异步)
     *
     * @param interfaceName 接口的名字,可以用全名或者接口名
     * @param methodName    方法名
     * @param args          方法参数
     * @param request       请求
     * @return 方法返回值
     */
    public static ServiceResult rpcApiToolAsync(String interfaceName, String methodName, List<Object> args, DefaultRequest request) {
        return getServiceResult(interfaceName, methodName, args, request, Boolean.TRUE, DEFAULT_PROCOTOL);
    }

    /**
     * rpc泛化接口调用类(异步)
     *
     * @param interfaceName 接口的名字,可以用全名或者接口名
     * @param methodName    方法名
     * @param args          方法参数
     * @param request       请求
     * @return 方法返回值
     */
    public static ServiceResult rpcApiToolAsync(String interfaceName, String methodName, Object args, DefaultRequest request) {
        return getServiceResult(interfaceName, methodName, Arrays.asList(args), request, Boolean.TRUE, DEFAULT_PROCOTOL);
    }

    private static ServiceResult getServiceResult(String interfaceName, String methodName, List<Object> args, DefaultRequest request, boolean async, String procotol) {
        try {
            if (!interfaceName.contains(INTERFACE_NAME_PACKAGE_SEPARATOR)) {
                interfaceName = String.format("indi.uhyils.service.%s", interfaceName);
            }
            // 获取执行接口
            GenericService genericService = getGenericService(interfaceName, async, procotol);


            /*
             * GenericService 这个接口只有一个方法，名为 $invoke，它接受三个参数，分别为方法名、方法参数类型数组和参数值数组；
             * 对于方法参数类型数组 如果是基本类型，如 int 或 long，可以使用 int.class.getName()获取其类型； 如果是基本类型数组，如
             * int[]，则可以使用 int[].class.getName()； 如果是 POJO，则直接使用全类名，如
             * org.apache.dubbo.samples.generic.api.Params。
             */
            //全部方法
            Method[] methods = Class.forName(interfaceName).getMethods();
            //获取指定方法
            Method method = Arrays.stream(methods).filter(m -> methodName.equalsIgnoreCase(m.getName()) && args.size() == m.getParameterTypes().length).findFirst().get();
            // 获取第一个参数(my所有rpc接口只有一个参数)
            Class params = method.getParameterTypes()[0];
            Object[] arg = args.toArray(new Object[0]);
            // 检测参数中有没有泛型,如果有,则直接将hashMap转为对应类实例
            arg[0] = RpcObjectTransUtil.changeObjRequestParadigm(arg[0], Class.forName(interfaceName), method);
            String parameterTypes = params.getName();
            if (genericService == null) {
                GenericService value = getGenericServiceReferenceConfig(interfaceName, async, procotol);
                genericService = value;
                MAP.put(interfaceName, value);
            }
            ServiceResult<Serializable> serviceResult = JSONObject.parseObject(JSONObject.toJSONString(genericService.invoke(methodName, new String[]{parameterTypes}, arg)), ServiceResult.class);
            if (serviceResult != null) {
                LogUtil.info(serviceResult.getRequestLink().toString());
            } else {
                LogUtil.info("返回值为空");
            }
            if (async == Boolean.FALSE) {
                // 添加链路
                request.setRequestLink(serviceResult.getRequestLink());
            }

            return serviceResult;
        } catch (Exception e) {
            LogUtil.error(RpcApiUtil.class, e);
            return ServiceResult.buildErrorResult("远程调用错误,具体见日志", request);
        }
    }

    /**
     * 获取GenericService(通用接口)
     *
     * @param interfaceName 接口名称
     * @param ansyn         是否是异步接口
     * @param procotol
     * @return
     */
    private static GenericService getGenericService(String interfaceName, boolean ansyn, String procotol) throws Exception {
        // 用GenericService可以替代所有接口引用
        GenericService genericService;
        if (MAP.containsKey(interfaceName)) {
            genericService = MAP.get(interfaceName);
            if (genericService == null) {
                MAP.remove(interfaceName);
                genericService = getGenericServiceReferenceConfig(interfaceName, ansyn, procotol);
                MAP.put(interfaceName, genericService);
            }
        } else {
            genericService = getGenericServiceReferenceConfig(interfaceName, ansyn, procotol);
            MAP.put(interfaceName, genericService);
        }
        return genericService;
    }

    private static GenericService getGenericServiceReferenceConfig(String interfaceName, boolean ansyn, String procotol) throws Exception {
        RpcConsumerBeanFieldInjectConfiguration bean = SpringUtil.getBean(RpcConsumerBeanFieldInjectConfiguration.class);
        Object registryOnCache = bean.getRegistryOnCache(interfaceName);
        return new GenericService(registryOnCache);
    }


}
