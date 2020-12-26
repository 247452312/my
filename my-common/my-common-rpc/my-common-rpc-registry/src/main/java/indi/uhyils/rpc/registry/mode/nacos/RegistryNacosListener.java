package indi.uhyils.rpc.registry.mode.nacos;

import com.alibaba.nacos.api.config.listener.Listener;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.registry.mode.RegistryListener;

import java.util.concurrent.Executor;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 18时49分
 */
public class RegistryNacosListener implements RegistryListener, Listener {
    @Override
    public void onMethodChange() {

    }

    @Override
    public void onInterfaceChange() {

    }

    @Override
    public void onServiceChange() {

    }

    @Override
    public void onServiceOnLine() {

    }

    @Override
    public void onServiceOffline() {

    }

    @Override
    public void setCluster(Cluster cluster) {

    }

    @Override
    public Executor getExecutor() {
        return null;
    }

    /**
     * 接收配置信息
     *
     * @param configInfo
     */
    @Override
    public void receiveConfigInfo(String configInfo) {

    }
}
