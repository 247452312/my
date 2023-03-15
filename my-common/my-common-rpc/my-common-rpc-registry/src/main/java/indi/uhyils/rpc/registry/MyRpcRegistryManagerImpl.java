package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.annotation.RpcSpi;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月04日 09时22分
 */
@RpcSpi
public class MyRpcRegistryManagerImpl implements MyRpcRegistryManager {

    /**
     * 允许应用对外提供服务
     */
    @Override
    public void allowProviderToPublish() {
        for (ProviderRegistry byClass : RpcContext.providerRegistry) {
            byClass.allowToPublish();
        }
    }

    /**
     * 禁止应用对外提供服务
     */
    @Override
    public void notAllowProviderToPublish() {
        for (ProviderRegistry byClass : RpcContext.providerRegistry) {
            byClass.notAllowToPublish();
        }
    }


    /**
     * 应用是否在完整向外发布服务
     *
     * @return
     */
    @Override
    public boolean isPublish() {
        for (ProviderRegistry byClass : RpcContext.providerRegistry) {
            if (Boolean.FALSE.equals(byClass.publishStatus())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 应用关闭通知回调
     */
    @Override
    public void closeHook() {
        for (ProviderRegistry byClass : RpcContext.providerRegistry) {
            byClass.close();
        }
        for (ConsumerRegistry byClass : RpcContext.consumerRegistry) {
            byClass.close();
        }


    }
}
