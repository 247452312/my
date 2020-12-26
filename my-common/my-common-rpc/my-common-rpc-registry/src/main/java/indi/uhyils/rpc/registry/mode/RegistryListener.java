package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.cluster.Cluster;

/**
 * 监听器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 18时45分
 */
public interface RegistryListener {

    /**
     * 方法改变的时候
     */
    void onMethodChange();

    /**
     * 接口改变的时候
     */
    void onInterfaceChange();

    /**
     * 方法改变的时候
     */
    void onServiceChange();


    /**
     * 在一个集群新服务上线的时候
     */
    void onServiceOnLine();

    /**
     * 在一个集群服务下线的时候
     */
    void onServiceOffline();

    /**
     * 设置目标
     *
     * @param cluster
     */
    void setCluster(Cluster cluster);
}
