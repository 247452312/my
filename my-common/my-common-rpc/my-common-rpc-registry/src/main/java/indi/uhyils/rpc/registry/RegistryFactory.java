package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.registry.content.RegistryContent;
import indi.uhyils.rpc.spi.RpcSpiManager;
import indi.uhyils.util.LogUtil;

/**
 * 注册中心类工厂
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时31分
 */
public class RegistryFactory {

    /**
     * 默认的registry
     */
    private static final String DEFAULT_REGISTRY = "default_consumer";

    /**
     * 配置中registry
     */
    private static final String REGISTRY_SPI_NAME = "registryConsumerSpi";

    /**
     * 默认的provider registry
     */
    private static final String DEFAULT_PROVIDER_REGISTRY = "default_provider";

    /**
     * 配置中provider registry
     */
    private static final String REGISTRY_PROVIDER_SPI_NAME = "registryProviderSpi";


    private RegistryFactory() {
    }

    /**
     * 创建一个消费者的注册层
     *
     * @param clazz 消费者对应的接口
     *
     * @return
     */
    public static ConsumerRegistry createConsumer(Class<?> clazz) throws InterruptedException {
        /*registry大体包含三大部分,1.cluster(负载均衡集群,下层) 2.要实现的class 3.和注册中心保持连接的mode*/
        // spi 获取消费者的注册者信息
        String registryName = (String) RpcConfigFactory.getCustomOrDefault(REGISTRY_SPI_NAME, DEFAULT_REGISTRY);
        // 返回一个构造完成的消费者
        ConsumerRegistry orGetExtensionByClass = (ConsumerRegistry) RpcSpiManager.createOrGetExtensionByClass(Registry.class, registryName, clazz);
        LogUtil.debug("rpc消费者创建成功,class:{}", clazz.getName());
        RegistryContent.CONSUMER_REGISTRY.add(orGetExtensionByClass);
        return orGetExtensionByClass;
    }


    /**
     * 创建一个生产者的注册层
     *
     * @param clazz 某个接口的数据
     *
     * @return
     */
    public static ProviderRegistry createProvider(Class<?> clazz, Object bean) throws InterruptedException {
        // spi 获取消费者的注册者信息
        String registryName = (String) RpcConfigFactory.getCustomOrDefault(REGISTRY_PROVIDER_SPI_NAME, DEFAULT_PROVIDER_REGISTRY);
        // 返回一个构造完成的生产者
        ProviderRegistry orGetExtensionByClass = (ProviderRegistry) RpcSpiManager.createOrGetExtensionByClass(Registry.class, registryName, clazz, bean);
        LogUtil.debug("rpc生产者创建成功,class:{}", clazz.getName());
        RegistryContent.PROVIDER_REGISTRY.add(orGetExtensionByClass);
        return orGetExtensionByClass;
    }

}
