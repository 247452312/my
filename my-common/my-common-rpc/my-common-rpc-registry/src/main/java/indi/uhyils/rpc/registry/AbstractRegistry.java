package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.cluster.Cluster;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时44分
 */
public abstract class AbstractRegistry<T> implements Registry<T> {
    /**
     * 集群
     */
    protected Cluster cluster;

    /**
     * 代表的service的类
     */
    protected Class<T> serviceClass;

    public AbstractRegistry(Cluster cluster, Class<T> serviceClass) {
        this.cluster = cluster;
        this.serviceClass = serviceClass;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public Class<T> getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class<T> serviceClass) {
        this.serviceClass = serviceClass;
    }
}
