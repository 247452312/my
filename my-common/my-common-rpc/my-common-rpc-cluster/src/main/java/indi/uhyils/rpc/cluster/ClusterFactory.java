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

import java.util.HashMap;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 18时50分
 */
public class ClusterFactory {

    public static Cluster createDefaultProviderCluster(String host, Integer port, Class<?> mainClass) throws Exception {
        NettyInitDto nettyInit = new NettyInitDto();
        nettyInit.setCallback(new RpcDefaultRequestCallBack(RpcBeanFactory.getInstance(mainClass).getRpcBeans()));
        nettyInit.setHost(host);
        nettyInit.setPort(port);
        RpcNetty netty = RpcNettyFactory.createNetty(RpcNettyTypeEnum.PROVIDER, nettyInit);
        NettyInfo nettyInfo = new NettyInfo();
        nettyInfo.setIndexInColony(1);
        return new ProviderDefaultCluster(nettyInfo, netty);
    }


    public static Cluster createDefaultConsumerCluster(NettyInitDto nettyInit) {

        RpcNetty netty = RpcNettyFactory.createNetty(RpcNettyTypeEnum.CONSUMER, nettyInit);
        NettyInfo nettyInfo = new NettyInfo();
        nettyInfo.setIndexInColony(1);
        HashMap<NettyInfo, RpcNetty> nettyMap = new HashMap<>(1);
        nettyMap.put(nettyInfo, netty);
        return new ConsumerDefaultCluster(nettyMap);
    }

    public static Cluster createDefaultConsumerCluster(NettyInitDto... nettyInit) {
        HashMap<NettyInfo, RpcNetty> nettyMap = new HashMap<>(nettyInit.length);
        for (int i = 0; i < nettyInit.length; i++) {
            RpcNetty netty = RpcNettyFactory.createNetty(RpcNettyTypeEnum.CONSUMER, nettyInit[i]);
            NettyInfo nettyInfo = new NettyInfo();
            nettyInfo.setIndexInColony(i);
            nettyMap.put(nettyInfo, netty);
        }

        return new ConsumerDefaultCluster(nettyMap);
    }


}
