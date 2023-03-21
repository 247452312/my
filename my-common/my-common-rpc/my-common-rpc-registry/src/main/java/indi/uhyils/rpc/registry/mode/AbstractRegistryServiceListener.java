package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.cluster.Cluster;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 18时53分
 */
public abstract class AbstractRegistryServiceListener implements RegistryServiceListener {

    /**
     * 监听器对负载均衡措施的指针
     */
    protected Map<String, Cluster> cluster;

    @Override
    public void setCluster(Map<String, Cluster> cluster) {
        this.cluster = cluster;
    }
}
