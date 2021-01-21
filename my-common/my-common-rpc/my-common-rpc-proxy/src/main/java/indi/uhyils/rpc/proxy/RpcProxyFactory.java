package indi.uhyils.rpc.proxy;

import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.proxy.handler.RpcInvokeHandler;
import indi.uhyils.rpc.registry.exception.RegistryException;

import java.lang.reflect.Proxy;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月28日 06时45分
 */
public class RpcProxyFactory {

    public static <T> T newProxy(Class<T> clazz, RpcConfig rpcConfig) throws RegistryException {
        if (!clazz.isInterface()) {
            throw new RegistryException("必须使用接口,您使用的是: " + clazz.getName());
        }
        Class<?>[] interfaces = clazz.getInterfaces();

        Class<?>[] classes = new Class[interfaces.length + 1];
        System.arraycopy(interfaces, 0, classes, 0, interfaces.length);
        classes[classes.length - 1] = clazz;
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), classes, new RpcInvokeHandler(clazz, rpcConfig));
        return (T) o;
    }
}
