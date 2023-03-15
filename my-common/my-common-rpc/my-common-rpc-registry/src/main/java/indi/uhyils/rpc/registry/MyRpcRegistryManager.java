package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.spi.RpcSpiExtension;

/**
 * 注册中心管理器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月04日 09时08分
 */
public interface MyRpcRegistryManager extends RpcSpiExtension {

    /**
     * 允许应用对外提供服务
     */
    void allowProviderToPublish();

    /**
     * 禁止应用对外提供服务
     */
    void notAllowProviderToPublish();


    /**
     * 应用是否在向外发布服务
     *
     * @return
     */
    boolean isPublish();


    /**
     * 应用关闭通知回调
     */
    void closeHook();
}
