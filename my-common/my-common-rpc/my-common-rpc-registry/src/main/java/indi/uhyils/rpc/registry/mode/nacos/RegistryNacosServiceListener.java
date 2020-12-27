package indi.uhyils.rpc.registry.mode.nacos;

import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.registry.mode.RegistryServiceListener;

import java.util.Map;
import java.util.concurrent.Executor;

/**
 * todo测试完毕后将这里补全
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 18时49分
 */
public class RegistryNacosServiceListener implements RegistryServiceListener, EventListener, Listener {
    @Override
    public void onMethodChange() {

    }

    @Override
    public void onInterfaceChange() {

    }

    @Override
    public void onServiceOnLine() {

    }

    @Override
    public void onServiceOffline() {

    }

    @Override
    public Map<String, Object> parseListenerInfo(String content) {
        return null;
    }

    @Override
    public void setCluster(Cluster cluster) {

    }

    @Override
    public void onEvent(Event event) {
        if (event instanceof NamingEvent) {
            NamingEvent namingEvent = (NamingEvent) event;
            System.out.println(namingEvent.getServiceName());
            System.out.println(namingEvent.getGroupName());
            System.out.println(namingEvent.getClusters());
        }
    }

    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public void receiveConfigInfo(String configInfo) {

    }
}
