package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.ClusterFactory;
import indi.uhyils.rpc.cluster.enums.LoadBalanceEnum;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.exchange.content.MyRpcContent;
import indi.uhyils.rpc.netty.callback.impl.RpcDefaultResponseCallBack;
import indi.uhyils.rpc.netty.factory.NettyInitDtoFactory;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;
import indi.uhyils.rpc.registry.pojo.info.RegistryProviderNecessaryInfo;
import indi.uhyils.rpc.registry.pojo.info.metadata.RegistryMetadata;
import indi.uhyils.rpc.registry.pojo.info.metadata.RegistryMetadataOfInterface;
import indi.uhyils.rpc.registry.pojo.info.metadata.RegistryMetadataOfMethod;
import indi.uhyils.rpc.spi.RpcSpiManager;
import indi.uhyils.util.IpUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 注册中心类工厂
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时31分
 */
public class RegistryFactory {


    /**
     * 默认的注册中心
     */
    private static final String DEFAULT_REGISTRY = "default_registry";

    private static final String REGISTRY_SPI_NAME = "registrySpi";

    /**
     * 创建一个消费者的注册层
     *
     * @param clazz 消费者对应的接口
     * @return
     */
    public static <T> Registry<T> createConsumer(Class<T> clazz) throws Exception {
        // spi 获取消费者
        String name = (String) RpcConfigFactory.getCustomOrDefault(REGISTRY_SPI_NAME, DEFAULT_REGISTRY);
        RegistryMode mode = (RegistryMode) RpcSpiManager.getExtensionByClass(RegistryMode.class, name);
        assert mode != null;
        List<RegistryInfo> targetInterfaceInfo = mode.getTargetInterfaceInfo(clazz.getName());
        NettyInitDto[] nettyInitDTOs = new NettyInitDto[targetInterfaceInfo.size()];
        for (int i = 0; i < targetInterfaceInfo.size(); i++) {
            RegistryInfo registryInfo = targetInterfaceInfo.get(i);
            RegistryProviderNecessaryInfo necessaryInfo = (RegistryProviderNecessaryInfo) registryInfo.getNecessaryInfo();
            double weight = necessaryInfo.getWeight();
            nettyInitDTOs[i] = NettyInitDtoFactory.createNettyInitDto(
                    necessaryInfo.getHost(),
                    necessaryInfo.getPort(),
                    (int) weight,
                    new RpcDefaultResponseCallBack());
        }
        Cluster defaultConsumerCluster = ClusterFactory.createDefaultConsumerCluster(clazz, nettyInitDTOs);
        return new ConsumerRegistry<>(defaultConsumerCluster, clazz, mode);
    }

    /**
     * 创建一个生产者的注册层
     *
     * @param clazz 某个接口的数据
     * @param <T>
     * @return
     */
    public static <T> Registry<T> createProvider(Class<T> clazz, Object bean, RegistryMode mode) throws Exception {
        RpcConfig config = RpcConfigFactory.getInstance();
        Integer port = config.getProvider().getPort();
        HashMap<String, Object> beans = new HashMap<>(1);
        beans.put(clazz.getName(), bean);
        Cluster providerCluster = ClusterFactory.createDefaultProviderCluster(port, beans);

        RegistryInfo info = new RegistryInfo();
        RegistryProviderNecessaryInfo necessaryInfo = new RegistryProviderNecessaryInfo();
        necessaryInfo.setHost(IpUtil.getIp());
        necessaryInfo.setPort(port);
        necessaryInfo.setRpcVersion(MyRpcContent.VERSION);
        necessaryInfo.setInterfaceName(clazz.getName());
        necessaryInfo.setHealth(true);
        necessaryInfo.setWeight(20d);
        necessaryInfo.setClusterName(clazz.getSimpleName());
        info.setNecessaryInfo(necessaryInfo);
        RegistryMetadata metadata = new RegistryMetadata();
        ArrayList<RegistryMetadataOfMethod> methodInfos = new ArrayList<>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            RegistryMetadataOfMethod metadataOfMethod = new RegistryMetadataOfMethod();
            metadataOfMethod.setName(method.getName());
            metadataOfMethod.setMethodParamTypes(Arrays.stream(method.getParameterTypes()).map(Class::getName).toArray(String[]::new));
            metadataOfMethod.setReturnType(method.getReturnType().getName());
            metadataOfMethod.setUseThisBalance(false);
            metadataOfMethod.setLoadBalance(LoadBalanceEnum.RANDOM.getCode());
            methodInfos.add(metadataOfMethod);
        }
        metadata.setMethodInfos(methodInfos);
        RegistryMetadataOfInterface serviceInfo = new RegistryMetadataOfInterface();
        serviceInfo.setServiceName(config.getApplication().getName());
        serviceInfo.setUseThisBalance(false);
        serviceInfo.setLoadBalance(LoadBalanceEnum.RANDOM.getCode());
        metadata.setServiceInfo(serviceInfo);
        info.setMetadata(metadata);
        mode.registry(info);
        return new ProviderRegistry<>(providerCluster, clazz, mode);
    }
}
