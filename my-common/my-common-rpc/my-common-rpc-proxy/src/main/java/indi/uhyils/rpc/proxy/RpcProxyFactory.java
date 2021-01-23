package indi.uhyils.rpc.proxy;

import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.proxy.generic.GenericService;
import indi.uhyils.rpc.proxy.handler.RpcProxyHandler;
import indi.uhyils.rpc.registry.exception.RegistryException;

import java.lang.reflect.Proxy;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月28日 06时45分
 */
public class RpcProxyFactory {

    private RpcProxyFactory() {

    }

    public static <T> T newProxy(Class<T> clazz) throws RegistryException {
        if (!clazz.isInterface()) {
            throw new RegistryException("必须使用接口,您使用的是: " + clazz.getName());
        }
        Class<?>[] interfaces = clazz.getInterfaces();

        Class<?>[] classes = new Class[interfaces.length + 1];
        System.arraycopy(interfaces, 0, classes, 0, interfaces.length);
        classes[classes.length - 1] = clazz;
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), classes, new RpcProxyHandler(clazz));
        return (T) o;
    }

    /**
     * 获取泛化接口
     *
     * @param clazz     对应的类
     * @param generic   是否泛化接口
     * @return
     * @throws RegistryException
     */
    public static Object newProxy(Class<?> clazz,  boolean generic) throws RegistryException {
        Object service = newProxy(clazz);
        return generic ? new GenericService(service) : service;
    }
}
