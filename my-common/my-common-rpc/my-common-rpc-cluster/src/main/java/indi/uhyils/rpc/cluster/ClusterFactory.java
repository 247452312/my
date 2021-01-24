package indi.uhyils.rpc.cluster;

import indi.uhyils.rpc.cluster.consumer.impl.ConsumerDefaultCluster;
import indi.uhyils.rpc.cluster.pojo.NettyInfo;
import indi.uhyils.rpc.cluster.provider.impl.ProviderDefaultCluster;
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
    public static Cluster createDefaultProviderCluster(Integer port, Map<String, Object> beans) throws Exception {
        if (instance == null) {
            synchronized (ClusterFactory.class) {
                if (instance == null) {
                    NettyInitDto nettyInit = new NettyInitDto();
                    nettyInit.setCallback(new RpcDefaultRequestCallBack(RpcBeanFactory.getInstance(beans).getRpcBeans()));
                    nettyInit.setHost(IpUtil.getIp());
                    nettyInit.setPort(port);
                    RpcNetty netty = RpcNettyFactory.createNetty(RpcNettyTypeEnum.PROVIDER, nettyInit);
                    NettyInfo nettyInfo = new NettyInfo();
                    nettyInfo.setIndexInColony(1);
                    instance = new ProviderDefaultCluster(nettyInfo, netty);
                }
            }
        }
        return instance;
    }

    public static Cluster createDefaultConsumerCluster(Class<?> clazz, NettyInitDto nettyInit) {
        return createDefaultConsumerCluster(clazz, new NettyInitDto[]{nettyInit});
    }

    public static Cluster createDefaultConsumerCluster(Class<?> clazz, NettyInitDto... nettyInits) {
        HashMap<NettyInfo, RpcNetty> nettyMap = new HashMap<>(nettyInits.length);
        for (int i = 0; i < nettyInits.length; i++) {
            NettyInitDto nettyInit = nettyInits[i];
            RpcNetty netty = RpcNettyFactory.createNetty(RpcNettyTypeEnum.CONSUMER, nettyInit);
            NettyInfo nettyInfo = new NettyInfo();
            nettyInfo.setHost(nettyInit.getHost());
            nettyInfo.setPort(nettyInit.getPort());
            nettyInfo.setWeight(nettyInit.getWeight());
            nettyInfo.setIndexInColony(i);
            nettyMap.put(nettyInfo, netty);
        }
        return new ConsumerDefaultCluster(clazz, nettyMap);
    }


}
