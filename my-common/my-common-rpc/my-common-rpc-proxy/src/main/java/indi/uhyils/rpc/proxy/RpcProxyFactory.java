package indi.uhyils.rpc.proxy;

import indi.uhyils.rpc.proxy.handler.RpcInvokeHandler;
import indi.uhyils.rpc.registry.exception.RegistryException;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月28日 06时45分
 */
public class RpcProxyFactory {

    public static <T> T newProxy(Class<T> clazz) throws RegistryException {
        if (!clazz.isInterface()) {
            throw new RegistryException("必须使用接口,您使用的是: " + clazz.getName());
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        List<Class<?>> interfaceList = new ArrayList<>(interfaces.length + 1);
        interfaceList.addAll(interfaceList);
        interfaceList.add(clazz);
        Class<?>[] classes = interfaceList.toArray(interfaces);
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), classes, new RpcInvokeHandler(clazz));
        return (T) o;
    }
}
