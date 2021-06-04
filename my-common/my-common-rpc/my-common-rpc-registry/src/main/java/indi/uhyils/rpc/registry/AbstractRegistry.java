package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.registry.mode.RegistryMode;

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
     * 和nacos连接的东西
     */
    protected RegistryMode mode;

    /**
     * 代表的service的类
     */
    protected Class<T> serviceClass;


    protected AbstractRegistry(Cluster cluster, Class<T> serviceClass) {
        this.cluster = cluster;
        this.serviceClass = serviceClass;
    }

    protected AbstractRegistry() {
    }

    /**
     * 如果使用默认的构造方法,则需要执行此方法进行初始化
     *
     * @param cluster
     * @param serviceClass
     */
    protected void init(Cluster cluster, Class<T> serviceClass) {
        this.cluster = cluster;
        this.serviceClass = serviceClass;
    }

    public RegistryMode getMode() {
        return mode;
    }

    public void setMode(RegistryMode mode) {
        this.mode = mode;
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
