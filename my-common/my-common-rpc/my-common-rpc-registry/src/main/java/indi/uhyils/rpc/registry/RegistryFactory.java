package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.ClusterFactory;
import indi.uhyils.rpc.cluster.enums.LoadBalanceEnum;
import indi.uhyils.rpc.exchange.content.MyRpcContent;
import indi.uhyils.rpc.netty.callback.impl.RpcDefaultResponseCallBack;
import indi.uhyils.rpc.netty.function.FunctionOne;
import indi.uhyils.rpc.netty.function.FunctionOneInterface;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;
import indi.uhyils.rpc.registry.pojo.info.RegistryProviderNecessaryInfo;
import indi.uhyils.rpc.registry.pojo.info.metadata.RegistryMetadata;
import indi.uhyils.rpc.registry.pojo.info.metadata.RegistryMetadataOfInterface;
import indi.uhyils.rpc.registry.pojo.info.metadata.RegistryMetadataOfMethod;
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
     * 创建一个消费者的注册层
     *
     * @param clazz 消费者对应的接口
     * @param host  消费者自己的ip
     * @param <T>
     * @return
     */
    public static <T> Registry<T> createConsumer(String serviceName, Class<T> clazz, String host, RegistryMode mode) throws Exception {

        List<RegistryInfo> targetInterfaceInfo = mode.getTargetInterfaceInfo(serviceName, clazz.getName());
        NettyInitDto[] nettyInitDtos = new NettyInitDto[targetInterfaceInfo.size()];
        for (int i = 0; i < targetInterfaceInfo.size(); i++) {
            RegistryInfo registryInfo = targetInterfaceInfo.get(i);
            NettyInitDto nettyInitDto = new NettyInitDto();
            RegistryProviderNecessaryInfo necessaryInfo = (RegistryProviderNecessaryInfo) registryInfo.getNecessaryInfo();
            nettyInitDto.setPort(necessaryInfo.getPort());
            nettyInitDto.setHost(necessaryInfo.getHost());
            nettyInitDto.setCallback(new RpcDefaultResponseCallBack());
            nettyInitDtos[i] = nettyInitDto;
        }
        Cluster defaultConsumerCluster = ClusterFactory.createDefaultConsumerCluster(nettyInitDtos);
        return new ConsumerRegistry<>(defaultConsumerCluster, clazz, host, mode);
    }

    /**
     * 创建一个生产者的注册层
     *
     * @param serviceName 服务名称
     * @param clazz       某个接口的数据
     * @param <T>
     * @return
     */
    public static <T> Registry<T> createProvider(String serviceName, Class<T> clazz, Integer port, RegistryMode mode) throws Exception {
        HashMap<String, Object> beans = new HashMap<>();
        FunctionOneInterface functionOneInterface = new FunctionOne();
        beans.put(functionOneInterface.getClass().getName(), functionOneInterface);
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
        serviceInfo.setServiceName(serviceName);
        serviceInfo.setUseThisBalance(false);
        serviceInfo.setLoadBalance(LoadBalanceEnum.RANDOM.getCode());
        metadata.setServiceInfo(serviceInfo);
        info.setMetadata(metadata);
        mode.registry(info);
        return new ProviderRegistry<>(providerCluster, clazz, mode);
    }
}
