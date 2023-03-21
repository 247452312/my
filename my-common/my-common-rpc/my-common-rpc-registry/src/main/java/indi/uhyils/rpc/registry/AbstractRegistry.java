package indi.uhyils.rpc.registry;

import com.alibaba.nacos.api.exception.NacosException;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.config.ProtocolConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.exception.MyRpcException;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.util.LogUtil;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时44分
 */
public abstract class AbstractRegistry<T> implements Registry<T> {

    /**
     * 协议
     */
    protected final ProtocolConfig protocolConfig;

    /**
     * 自己的rpc端口
     */
    protected final Integer selfPort;

    /**
     * 集群
     */
    protected Map<String, Cluster> clusters;

    /**
     * 和nacos连接的东西
     */
    protected RegistryMode mode;

    /**
     * 代表的service的类
     */
    protected Class<T> serviceClass;

    protected AbstractRegistry() {
        this.protocolConfig = RpcConfigFactory.getInstance().getProtocol();
        this.selfPort = RpcConfigFactory.getInstance().getProvider().getPort();
    }


    /**
     * 如果使用默认的构造方法,则需要执行此方法进行初始化
     */
    @Override
    public void init(Object... objects) throws InterruptedException {
        serviceClass = (Class<T>) objects[0];
        try {
            doRegistryInit(objects);
        } catch (NacosException | MyRpcException e) {
            LogUtil.error(this, e);
        }
    }

    /**
     * 初始化
     *
     * @param objects
     */
    protected abstract void doRegistryInit(Object... objects) throws NacosException, MyRpcException, InterruptedException;


    public RegistryMode getMode() {
        return mode;
    }

    public void setMode(RegistryMode mode) {
        this.mode = mode;
    }

    public Map<String, Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(Map<String, Cluster> clusters) {
        this.clusters = clusters;
    }

    public Class<T> getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class<T> serviceClass) {
        this.serviceClass = serviceClass;
    }

}
