package indi.uhyils.rpc.cluster.consumer.impl;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.enums.LoadBalanceEnum;
import indi.uhyils.rpc.cluster.load.LoadBalanceFactory;
import indi.uhyils.rpc.cluster.load.LoadBalanceInterface;
import indi.uhyils.rpc.cluster.pojo.NettyInfo;
import indi.uhyils.rpc.cluster.pojo.SendInfo;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.netty.RpcNetty;
import indi.uhyils.rpc.netty.callback.impl.RpcDefaultResponseCallBack;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.netty.factory.RpcNettyFactory;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;
import indi.uhyils.util.LogUtil;

import java.util.*;

/**
 * 消费者默认的cluster
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 12时23分
 */
@RpcSpi(single = false)
public class ConsumerDefaultCluster implements Cluster {
    /**
     * 需要负载均衡的netty们
     */
    private Map<NettyInfo, RpcNetty> nettyMap;

    /**
     * 负载均衡策略
     */
    private LoadBalanceEnum loadBalanceType;

    /**
     * 接口名称
     */
    private Class<?> target;

    public ConsumerDefaultCluster() {
    }

    @Override
    public void init(Object... params) throws Exception {
        Class clazz = (Class) params[0];
        NettyInitDto[] nettyInits = (NettyInitDto[]) params[1];
        if (params.length > 2) {
            this.loadBalanceType = (LoadBalanceEnum) params[2];
        } else {
            this.loadBalanceType = LoadBalanceEnum.RANDOM;
        }

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
        this.nettyMap = nettyMap;
        this.target = clazz;
    }

    @Override
    public String getInterfaceName() {
        return this.target.getName();
    }

    @Override
    public LoadBalanceEnum getTypeOfLoadBalance() {
        return loadBalanceType;
    }

    @Override
    public Boolean isSingle() {
        return nettyMap.size() == 1;
    }

    @Override
    public Integer getNumOfColony() {
        return nettyMap.size();
    }

    @Override
    public Map<NettyInfo, RpcNetty> getAllNetty() {
        return nettyMap;
    }

    @Override
    public Boolean shutdown() {
        boolean result = Boolean.TRUE;
        for (Map.Entry<NettyInfo, RpcNetty> nettyInfoRpcNettyEntry : nettyMap.entrySet()) {
            boolean shutdown = nettyInfoRpcNettyEntry.getValue().shutdown();
            if (!shutdown) {
                result = Boolean.FALSE;
            }
        }
        return result;
    }

    @Override
    public RpcData sendMsg(RpcData rpcData, SendInfo info) throws RpcException {
        if (nettyMap.isEmpty()) {
            String interfaceName = getInterfaceName();
            throw new RpcException("指定的服务端" + interfaceName + "不存在");
        }
        try {
            LoadBalanceInterface loadBalance = LoadBalanceFactory.createByLoadBalanceEnum(loadBalanceType, nettyMap);
            return loadBalance.send(rpcData, info, nettyMap);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
        return null;
    }

    @Override
    public Boolean onServiceStatusChange(List<NettyInfo> nettyInfos) throws Exception {
        // 筛选出没有的,移出->下线
        Set<NettyInfo> set = new HashSet<>();
        for (NettyInfo nettyInfo : nettyMap.keySet()) {
            if (!nettyInfos.contains(nettyInfo)) {
                set.add(nettyInfo);
            }
        }
        for (NettyInfo nettyInfo : set) {
            RpcNetty rpcNetty = nettyMap.get(nettyInfo);
            rpcNetty.shutdown();
            nettyMap.remove(nettyInfo);
        }
        // 筛选不存在的,添加->上线
        for (int i = 0; i < nettyInfos.size(); i++) {
            NettyInfo nettyInfo = nettyInfos.get(i);
            if (nettyMap.containsKey(nettyInfo)) {
                continue;
            }
            NettyInitDto nettyInit = new NettyInitDto();
            nettyInit.setHost(nettyInfo.getHost());
            nettyInit.setPort(nettyInfo.getPort());
            nettyInit.setCallback(new RpcDefaultResponseCallBack());
            RpcNetty netty = RpcNettyFactory.createNetty(RpcNettyTypeEnum.CONSUMER, nettyInit);
            nettyMap.put(nettyInfo, netty);
        }
        return Boolean.TRUE;
    }
}
