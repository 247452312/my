package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.ClusterFactory;
import indi.uhyils.rpc.cluster.pojo.SendInfo;
import indi.uhyils.rpc.content.ClusterNameContext;
import indi.uhyils.rpc.exception.RpcNetException;
import indi.uhyils.rpc.exception.RpcShowDownException;
import indi.uhyils.rpc.exchange.pojo.content.impl.RpcRequestContentImpl;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.callback.RpcCallBackFactory;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.netty.factory.NettyInitDtoFactory;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.rpc.registry.mode.RegistryModeFactory;
import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;
import indi.uhyils.rpc.registry.pojo.info.RegistryProviderNecessaryInfo;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.IpUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.MapUtil;
import indi.uhyils.util.StringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时43分
 */
@RpcSpi(single = false)
public class ConsumerRegistryImpl<T> extends AbstractRegistry<T> implements ConsumerRegistry<T> {


    /**
     * 发送者的ip
     */
    private final String selfIp;

    public ConsumerRegistryImpl() {
        this.selfIp = IpUtil.getIp();
    }

    @Override
    public RpcData invoke(RpcData rpcData) throws InterruptedException {
        String clusterName = ClusterNameContext.get();
        if (StringUtil.isNotEmpty(clusterName)) {
            Asserts.assertTrue(clusters.containsKey(clusterName), "执行时集群中不存在指定的集群名称:{}", clusterName);
            Cluster cluster = clusters.get(clusterName);
            return cluster.sendMsg(rpcData, new SendInfo(selfIp));
        }
        Optional<Entry<String, Cluster>> any = clusters.entrySet().stream().findAny();
        RpcRequestContentImpl content = (RpcRequestContentImpl) rpcData.content();
        Asserts.assertTrue(any.isPresent(), "指定接口未发现可用的服务:{},版本:{}", content.getServiceName(), content.getServiceVersion());
        Entry<String, Cluster> stringClusterEntry = any.get();
        Cluster value = stringClusterEntry.getValue();
        return value.sendMsg(rpcData, new SendInfo(selfIp));
    }

    @Override
    public void close() {
        Boolean down = true;
        for (Entry<String, Cluster> clusterEntry : clusters.entrySet()) {
            Cluster value = clusterEntry.getValue();
            Boolean shutdown = value.shutdown();
            if (!shutdown) {
                down = false;
            }
        }
        if (!down) {
            throw new RpcShowDownException("rpc关闭错误");
        }
    }

    @Override
    protected void doRegistryInit(Object... objects) throws InterruptedException {
        RegistryMode mode = RegistryModeFactory.create();
        mode.setType(RpcNettyTypeEnum.CONSUMER);
        this.mode = mode;

        try {
            this.clusters = createClusters();
            // 创建监听器.监听注册中心
            mode.createListener(this.serviceClass.getName(), clusters);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
    }

    /**
     * 创建消费者的cluster,包含了目标的cluster
     *
     * @return key->集群名称, value->集群
     */
    private Map<String, Cluster> createClusters() throws InterruptedException {

        /*构建netty初始化需要的信息*/
        Map<String, List<RegistryInfo>> remoteRegistryInfos = this.mode.getTargetInterfaceInfo(this.serviceClass.getName());

        if (MapUtil.isEmpty(remoteRegistryInfos)) {
            throw new RpcNetException("不存在指定的服务:" + this.serviceClass.getName());
        }
        Map<String, Cluster> results = new HashMap<>(remoteRegistryInfos.size());
        for (Entry<String, List<RegistryInfo>> registryEntity : remoteRegistryInfos.entrySet()) {
            Cluster cluster = createCluster(registryEntity.getValue());
            results.put(registryEntity.getKey(), cluster);
        }
        return results;

    }

    /**
     * 创建一个单独的集群
     *
     * @param registryInfos 注册信息
     *
     * @return
     */
    private Cluster createCluster(List<RegistryInfo> registryInfos) throws InterruptedException {
        // 获取目标接口的信息
        NettyInitDto[] nettyInits = new NettyInitDto[registryInfos.size()];
        for (int i = 0; i < registryInfos.size(); i++) {
            RegistryInfo registryInfo = registryInfos.get(i);
            //查询到目标class注册到注册中心的信息
            RegistryProviderNecessaryInfo necessaryInfo = (RegistryProviderNecessaryInfo) registryInfo.getNecessaryInfo();

            // 是否允许调用自身
            if (Boolean.TRUE.equals(protocolConfig.getAutoUseSelf()) && isSelfService(necessaryInfo.getHost(), necessaryInfo.getPort())) {
                nettyInits[i] = NettyInitDtoFactory.createSelfNettyInitDto();
                continue;
            }
            nettyInits[i] = NettyInitDtoFactory.createNettyInitDto(necessaryInfo.getHost(), necessaryInfo.getPort(), necessaryInfo.getWeight().intValue(), RpcCallBackFactory.createResponseCallBack());
        }
        try {
            return ClusterFactory.createDefaultConsumerCluster(this.serviceClass, nettyInits);
        } catch (Exception e) {
            LogUtil.error(this, e);
            return null;
        }
    }

    /**
     * 判断是否是自己的服务
     *
     * @param host
     * @param port
     *
     * @return
     */
    private boolean isSelfService(String host, Integer port) {
        return Objects.equals(host, selfIp) && Objects.equals(selfPort, port);
    }


}
