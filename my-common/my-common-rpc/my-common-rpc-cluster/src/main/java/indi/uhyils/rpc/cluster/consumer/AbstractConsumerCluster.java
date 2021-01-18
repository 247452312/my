package indi.uhyils.rpc.cluster.consumer;

import com.alibaba.fastjson.JSON;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.netty.RpcNetty;
import indi.uhyils.rpc.pojo.RpcData;
import indi.uhyils.rpc.pojo.response.RpcResponseContent;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 12时21分
 */
public abstract class AbstractConsumerCluster implements Cluster {

    /**
     * 通过负载均衡去获取指定请求的回应
     *
     * @param rpcData     rpc请求
     * @param targetClass 目标class
     * @param <T>         返回值
     * @return
     */
    public abstract <T> T invoke(RpcData rpcData, Class<T> targetClass);

    /***
     * 通过指定一个netty去获取相应的回应
     *
     * @param rpcData
     * @param rpcNetty
     * @return
     */
    public <T> T invode(RpcData rpcData, RpcNetty rpcNetty, Class<T> targetClass) throws InterruptedException {
        rpcNetty.sendMsg(rpcData.toBytes());
        // 远程过程调用
        RpcData data = rpcNetty.wait(rpcData.unique());
        RpcResponseContent content = (RpcResponseContent) data.content();
        return JSON.parseObject(content.getResponseContent(), targetClass);
    }
}
