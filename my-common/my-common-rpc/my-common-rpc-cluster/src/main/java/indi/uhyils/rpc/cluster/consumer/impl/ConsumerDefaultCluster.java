package indi.uhyils.rpc.cluster.consumer.impl;

import indi.uhyils.rpc.cluster.consumer.AbstractConsumerCluster;
import indi.uhyils.rpc.cluster.enums.LoadBalanceEnum;
import indi.uhyils.rpc.cluster.pojo.NettyInfo;
import indi.uhyils.rpc.cluster.pojo.SendInfo;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.netty.RpcNetty;
import indi.uhyils.rpc.netty.callback.impl.RpcDefaultResponseCallBack;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.netty.factory.RpcNettyFactory;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 12时23分
 */
public class ConsumerDefaultCluster extends AbstractConsumerCluster {
    /**
     * 需要负载均衡的netty们
     */
    private final Map<NettyInfo, RpcNetty> nettyMap;

    /**
     * 负载均衡策略
     */
    private final LoadBalanceEnum loadBalanceType;

    /**
     * 如果是轮询时的标记
     */
    private final AtomicInteger pollingMark = new AtomicInteger(0);

    /**
     * 权重分配的标记
     */
    private volatile NettyInfo[] weightArrayForManualAssignment;

    /**
     * 配置
     */
    private RpcConfig config;

    public ConsumerDefaultCluster(RpcConfig config, Map<NettyInfo, RpcNetty> nettyMap, LoadBalanceEnum loadBalanceType) {
        this.config = config;
        this.nettyMap = nettyMap;
        this.loadBalanceType = loadBalanceType;
    }

    public ConsumerDefaultCluster(RpcConfig config, Map<NettyInfo, RpcNetty> nettyMap) {
        this(config, nettyMap, LoadBalanceEnum.RANDOM);
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
        boolean result = true;
        for (Map.Entry<NettyInfo, RpcNetty> nettyInfoRpcNettyEntry : nettyMap.entrySet()) {
            Boolean shutdown = nettyInfoRpcNettyEntry.getValue().shutdown();
            if (!shutdown) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public RpcData sendMsg(RpcData rpcData, SendInfo info) throws InterruptedException, RpcException {
        if (nettyMap.size() == 0) {
            throw new RpcException("指定的服务端不存在");
        }
        //todo 暂时没有实现最少活跃
        switch (loadBalanceType) {
            case LEAST_ACTIVE:
                // 这里需要修改为:n秒内调用成功次数
            case RANDOM:
                return randomSend(rpcData);
            case IP_HASH:
                return sendByIndex(rpcData, info.getIp().hashCode());
            case POLLING:
                return sendByPolling(rpcData);
            case MANUAL_ASSIGNMENT:
                return manualAssignmentByWeight(rpcData);
            case FASTEST_RETURN_SPEED:
                return sendByFastestReturnSpeed(rpcData);
            default:
                return null;
        }
    }


    private RpcData sendByFastestReturnSpeed(RpcData rpcData) throws InterruptedException {
        NettyInfo fastNettyInfo = null;
        long minTime = 0;
        for (NettyInfo nettyInfo : nettyMap.keySet()) {
            Long lastFiveSendAvgTime = nettyInfo.getLastFiveSendAvgTime();
            // 如果一个netty一次都没有执行过,那么就选它
            if (lastFiveSendAvgTime == null) {
                fastNettyInfo = nettyInfo;
                break;
            }
            if (lastFiveSendAvgTime < minTime) {
                minTime = lastFiveSendAvgTime;
                fastNettyInfo = nettyInfo;
            }
        }
        assert fastNettyInfo != null;
        long startTime = System.currentTimeMillis();
        RpcNetty rpcNetty = nettyMap.get(fastNettyInfo);
        Boolean aBoolean = rpcNetty.sendMsg(rpcData.toBytes());
        if (aBoolean) {
            RpcData wait = rpcNetty.wait(rpcData.unique());
            long runTime = System.currentTimeMillis() - startTime;
            Long lastFiveSendAvgTime = fastNettyInfo.getLastFiveSendAvgTime();
            if (lastFiveSendAvgTime == null) {
                lastFiveSendAvgTime = runTime;
            }
            long lastTime = (lastFiveSendAvgTime * 5 + runTime) / 6L;
            fastNettyInfo.setLastFiveSendAvgTime(lastTime);
            return wait;
        }
        return null;
    }

    private RpcData manualAssignmentByWeight(RpcData rpcData) throws InterruptedException {
        if (weightArrayForManualAssignment == null) {
            Integer length = 0;
            for (NettyInfo nettyInfo : nettyMap.keySet()) {
                length += nettyInfo.getWeight();
            }
            weightArrayForManualAssignment = new NettyInfo[length];
            int writeIndex = 0;
            for (NettyInfo nettyInfo : nettyMap.keySet()) {
                Integer weight = nettyInfo.getWeight();
                for (int i = 0; i < weight; i++, writeIndex++) {
                    weightArrayForManualAssignment[writeIndex] = nettyInfo;
                }
            }
        }

        int i = RandomUtils.nextInt(0, weightArrayForManualAssignment.length);
        RpcNetty rpcNetty = nettyMap.get(weightArrayForManualAssignment[i]);
        Boolean aBoolean = rpcNetty.sendMsg(rpcData.toBytes());
        if (aBoolean) {
            return rpcNetty.wait(rpcData.unique());
        }
        return null;
    }

    private RpcData sendByPolling(RpcData rpcData) throws InterruptedException, RpcException {
        int pollIndex = pollingMark.getAndAdd(1);
        if (pollIndex > nettyMap.size()) {
            pollingMark.set(0);
            return sendByIndex(rpcData, 0);
        } else {
            return sendByIndex(rpcData, pollIndex);
        }
    }

    private RpcData randomSend(RpcData rpcData) throws InterruptedException, RpcException {
        int i = RandomUtils.nextInt(0, nettyMap.size());
        return sendByIndex(rpcData, i);
    }

    private RpcData sendByIndex(RpcData rpcData, int i) throws InterruptedException, RpcException {
        i = i % nettyMap.size();
        Object o = nettyMap.keySet().toArray()[i];
        RpcNetty rpcNetty = nettyMap.get(o);
        Boolean aBoolean = rpcNetty.sendMsg(rpcData.toBytes());
        if (aBoolean) {
            return rpcNetty.wait(rpcData.unique());
        }
        return null;
    }

    @Override
    public RpcData wait(Long unique) throws InterruptedException {
        return null;
    }

    @Override
    public Boolean onServiceStatusChange(List<NettyInfo> nettyInfos) {
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
            RpcNetty netty = RpcNettyFactory.createNetty(this.config, RpcNettyTypeEnum.CONSUMER, nettyInit);
            nettyMap.put(nettyInfo, netty);
        }
        return true;
    }

    @Override
    public <T> T invoke(RpcData rpcData, Class<T> targetClass) {
        return null;
    }
}
