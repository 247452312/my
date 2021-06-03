package indi.uhyils.rpc.proxy.handler;

import indi.uhyils.rpc.spi.RpcSpiExtension;

import java.lang.reflect.InvocationHandler;

/**
 * rpc的interface扩展点,如果需要扩展proxy,分五步:
 * 1.需要实现此方法(必须)
 * 2.使用{@link indi.uhyils.rpc.annotation.RpcSpi}注解(必须)
 * 3.在META-INF/rpc中新建文件 名称: indi.uhyils.rpc.proxy.handler.RpcProxyHandlerInterface(必须)
 * 4.在新建的文件中写明要扩展的类的名称以及全称 例如: xxx_rpc_spi_proxy=indi.uhyils.rpc.proxy.handler.RpcProxyDefaultHandler(可选,如果在扫描范围内可不填,那么名称默认是类名首字母小写)
 * 5.在配置中写明rpc.custom.proxy=您要实现扩展的类的名称(如果是自己实现的,则必须,使用系统默认的,则不需要)
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月01日 08时23分
 */
public interface RpcProxyHandlerInterface extends InvocationHandler, RpcSpiExtension {

    /**
     * 初始化handler用
     *
     * @param clazz
     */
    void init(Class<?> clazz);
}
