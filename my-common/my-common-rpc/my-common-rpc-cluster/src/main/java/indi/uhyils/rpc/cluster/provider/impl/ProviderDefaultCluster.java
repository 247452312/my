package indi.uhyils.rpc.cluster.provider.impl;

import indi.uhyils.rpc.cluster.enums.LoadBalanceEnum;
import indi.uhyils.rpc.cluster.pojo.NettyInfo;
import indi.uhyils.rpc.cluster.pojo.SendInfo;
import indi.uhyils.rpc.cluster.provider.AbstractProviderCluster;
import indi.uhyils.rpc.netty.RpcNetty;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 15时36分
 */
public class ProviderDefaultCluster extends AbstractProviderCluster {
    /**
     * netty信息
     */
    private NettyInfo nettyInfo;

    /**
     * netty的本体
     */
    private RpcNetty netty;

    /**
     * 负载均衡策略
     */
    private LoadBalanceEnum loadBalanceType;

    public ProviderDefaultCluster(NettyInfo nettyInfo, RpcNetty netty, LoadBalanceEnum loadBalanceType) {
        this.nettyInfo = nettyInfo;
        this.netty = netty;
        this.loadBalanceType = loadBalanceType;
    }

    public ProviderDefaultCluster(NettyInfo nettyInfo, RpcNetty netty) {
        this(nettyInfo, netty, LoadBalanceEnum.RANDOM);
    }

    @Override
    public LoadBalanceEnum getTypeOfLoadBalance() {
        return loadBalanceType;
    }

    @Override
    public Boolean isSingle() {
        return true;
    }

    @Override
    public Integer getNumOfColony() {
        return 1;
    }

    @Override
    public Map<NettyInfo, RpcNetty> getAllNetty() {
        Map<NettyInfo, RpcNetty> nettyInfoRpcNettyMap = new HashMap<>(1);
        nettyInfoRpcNettyMap.put(nettyInfo, netty);
        return nettyInfoRpcNettyMap;
    }

    @Override
    public Boolean shutdown() {
        return netty.shutdown();
    }

    @Override
    public RpcData sendMsg(RpcData rpcData, SendInfo info) throws InterruptedException {
        Boolean aBoolean = netty.sendMsg(rpcData.toBytes());
        if (aBoolean) {
            return netty.wait(rpcData.unique());
        } else {
            return null;
        }
    }

    @Override
    public RpcData wait(Long unique) throws InterruptedException {
        return netty.wait(unique);
    }

    @Override
    public Boolean onServiceStatusChange(List<NettyInfo> nettyInfos) {
        return true;
    }

    @Override
    public Integer getIndexInColony() {
        return 1;
    }

    @Override
    public Float getWeight() {
        return 0F;
    }
}
