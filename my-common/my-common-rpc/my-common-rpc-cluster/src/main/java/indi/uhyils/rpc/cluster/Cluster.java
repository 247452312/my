package indi.uhyils.rpc.cluster;

import indi.uhyils.rpc.cluster.enums.LoadBalanceEnum;
import indi.uhyils.rpc.cluster.pojo.NettyInfo;
import indi.uhyils.rpc.cluster.pojo.SendInfo;
import indi.uhyils.rpc.netty.RpcNetty;
import indi.uhyils.rpc.netty.pojo.RpcData;

import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月25日 09时29分
 */
public interface Cluster {


    /**
     * 获取负载均衡方式
     *
     * @return
     */
    LoadBalanceEnum getTypeOfLoadBalance();

    /**
     * 获取cluster中是否是单例,例如 provider就一定是单例
     *
     * @return
     */
    Boolean isSingle();

    /**
     * 获取集群数量
     *
     * @return
     */
    Integer getNumOfColony();


    /**
     * 获取此cluster下所有的netty
     *
     * @return
     */
    Map<NettyInfo, RpcNetty> getAllNetty();


    /**
     * 关闭,不会立即关闭.会等待线程结束
     *
     * @return
     */
    Boolean shutdown();

    /**
     * 发送信息
     *
     * @param rpcData
     * @param info
     * @return
     * @throws InterruptedException
     */
    RpcData sendMsg(RpcData rpcData, SendInfo info) throws InterruptedException;

    /**
     * 等待执行long类型的回应
     *
     * @param unique
     * @return
     * @throws InterruptedException
     */
    RpcData wait(Long unique) throws InterruptedException;


}
