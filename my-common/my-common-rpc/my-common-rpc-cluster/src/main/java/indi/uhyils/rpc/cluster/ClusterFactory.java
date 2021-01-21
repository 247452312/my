package indi.uhyils.rpc.cluster;

import indi.uhyils.rpc.cluster.consumer.impl.ConsumerDefaultCluster;
import indi.uhyils.rpc.cluster.pojo.NettyInfo;
import indi.uhyils.rpc.cluster.provider.impl.ProviderDefaultCluster;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.factory.RpcBeanFactory;
import indi.uhyils.rpc.netty.RpcNetty;
import indi.uhyils.rpc.netty.callback.impl.RpcDefaultRequestCallBack;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.netty.factory.RpcNettyFactory;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;
import indi.uhyils.util.IpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 18时50分
 */
public class ClusterFactory {

    private volatile static Cluster instance;

    private ClusterFactory() {
    }

    /**
     * 如果在同一个服务中,那么共用同一个ProviderCluster
     *
     * @param port
     * @param beans
     * @return
     * @throws Exception
     */
    public static Cluster createDefaultProviderCluster(RpcConfig config, Integer port, Map<String, Object> beans) throws Exception {
        if (instance == null) {
            synchronized (ClusterFactory.class) {
                if (instance == null) {
                    NettyInitDto nettyInit = new NettyInitDto();
                    nettyInit.setCallback(new RpcDefaultRequestCallBack(RpcBeanFactory.getInstance(beans).getRpcBeans()));
                    nettyInit.setHost(IpUtil.getIp());
                    nettyInit.setPort(port);
                    RpcNetty netty = RpcNettyFactory.createNetty(config, RpcNettyTypeEnum.PROVIDER, nettyInit);
                    NettyInfo nettyInfo = new NettyInfo();
                    nettyInfo.setIndexInColony(1);
                    instance = new ProviderDefaultCluster(nettyInfo, netty);
                }
            }
        }
        return instance;
    }

    public static Cluster createDefaultConsumerCluster(RpcConfig config, NettyInitDto nettyInit) {

        RpcNetty netty = RpcNettyFactory.createNetty(config, RpcNettyTypeEnum.CONSUMER, nettyInit);
        NettyInfo nettyInfo = new NettyInfo();
        nettyInfo.setIndexInColony(1);
        HashMap<NettyInfo, RpcNetty> nettyMap = new HashMap<>(1);
        nettyMap.put(nettyInfo, netty);
        return new ConsumerDefaultCluster(config, nettyMap);
    }

    public static Cluster createDefaultConsumerCluster(RpcConfig rpcConfig, NettyInitDto... nettyInit) {
        HashMap<NettyInfo, RpcNetty> nettyMap = new HashMap<>(nettyInit.length);
        for (int i = 0; i < nettyInit.length; i++) {
            RpcNetty netty = RpcNettyFactory.createNetty(rpcConfig, RpcNettyTypeEnum.CONSUMER, nettyInit[i]);
            NettyInfo nettyInfo = new NettyInfo();
            nettyInfo.setIndexInColony(i);
            nettyMap.put(nettyInfo, netty);
        }
        return new ConsumerDefaultCluster(rpcConfig, nettyMap);
    }


}
