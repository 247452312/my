package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.cluster.Cluster;

import java.util.Map;

/**
 * 监听器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 18时45分
 */
public interface RegistryServiceListener {

    /**
     * 方法改变的时候
     */
    void onMethodChange();

    /**
     * 接口改变的时候
     */
    void onInterfaceChange();

    /**
     * 在一个集群新服务上线的时候
     */
    void onServiceOnLine();

    /**
     * 在一个集群服务下线的时候
     */
    void onServiceOffline();

    /**
     * 解析
     *
     * @param content
     * @return
     */
    Map<String, Object> parseListenerInfo(String content);

    /**
     * 设置集群
     *
     * @param cluster
     */
    void setCluster(Cluster cluster);
}
