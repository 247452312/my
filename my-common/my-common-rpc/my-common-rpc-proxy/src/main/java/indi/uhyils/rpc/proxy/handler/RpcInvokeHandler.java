package indi.uhyils.rpc.proxy.handler;

import com.alibaba.fastjson.JSON;
import indi.uhyils.rpc.registry.Registry;
import indi.uhyils.rpc.registry.RegistryFactory;
import indi.uhyils.rpc.registry.content.RegistryContent;
import indi.uhyils.rpc.registry.mode.nacos.RegistryNacosMode;
import indi.uhyils.util.IpUtil;
import indi.uhyils.util.LogUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月28日 06时47分
 */
public class RpcInvokeHandler implements InvocationHandler {
    private static final String TO_STRING = "toString";
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 注册类
     */
    private Registry registry;
    /**
     * 注册中心的host
     */
    private String registryHost;
    /**
     * 注册中心的端口
     */
    private Integer registryPort;

    public RpcInvokeHandler(Class clazz) {
//        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        this.serviceName = RegistryContent.DEFAULT_REGISTRY_GROUP_NAME;
//        String[] property = applicationContext.getEnvironment().getProperty("dubbo.registry.address").split(":");
        this.registryHost = "192.168.1.101";
        this.registryPort = 8848;
        RegistryNacosMode mode = null;
        try {
            mode = new RegistryNacosMode(this.registryHost, this.registryPort);
            this.registry = RegistryFactory.createConsumer(serviceName, clazz, IpUtil.getIp(), mode);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        IdUtil bean = SpringUtil.getBean(IdUtil.class);

        if (TO_STRING.equals(method.getName())) {
            return "this is the interface,it`s name is " + proxy.getClass().getSimpleName();
        }
        String invoke = registry.invoke(1L, method.getName(), method.getParameterTypes(), args);
        Class<?> returnType = method.getReturnType();
        return JSON.parseObject(invoke, returnType);
    }
}
