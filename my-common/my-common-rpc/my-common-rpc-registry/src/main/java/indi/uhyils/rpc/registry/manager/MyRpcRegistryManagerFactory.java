package indi.uhyils.rpc.registry.manager;

import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.spi.RpcSpiManager;

/**
 *
 * @date 文件创建日期 2023年04月24日 11时09分
 * @author uhyils <247452312@qq.com>
 */
public class MyRpcRegistryManagerFactory {

    /**
     * 默认的rpcManager
     */
    private static final String DEFAULT_REGISTER_MANAGER = "default_register_manager";

    /**
     * 配置中rpcManager
     */
    private static final String REGISTRY_MANAGER_SPI = "registryManagerSpi";

    /**
     * 创建一个生产者的注册层
     *
     * @return
     */
    public static MyRpcRegistryManager createOrGetMyRpcRegistryManager() throws InterruptedException {
        // spi 获取消费者的注册者信息
        String registryName = (String) RpcConfigFactory.getCustomOrDefault(REGISTRY_MANAGER_SPI, DEFAULT_REGISTER_MANAGER);
        // 返回一个构造完成的生产者
        return (MyRpcRegistryManager) RpcSpiManager.createOrGetExtensionByClass(MyRpcRegistryManager.class, registryName, null);
    }

}
