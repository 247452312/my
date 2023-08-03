package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.registry.mode.RegistryCenterHandler;

/**
 * 注册中心层模板
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年04月23日 15时48分
 */
public abstract class AbstractRegistry implements Registry {


    /**
     * 注册中心层对应的模板
     */
    protected Class<?> serviceClass;

    /**
     * 注册中心监听连接句柄
     */
    protected RegistryCenterHandler registryCenterHandler;

    /**
     * 如果使用默认的构造方法,则需要执行此方法进行初始化
     */
    @Override
    public void init(Object... objects) throws InterruptedException {
        serviceClass = (Class<?>) objects[0];
        registryCenterHandler = doInitRegistryCenterHandler(objects);
    }

    /**
     * 初始化连接句柄
     */
    protected abstract RegistryCenterHandler doInitRegistryCenterHandler(Object... objects) throws InterruptedException;


}
