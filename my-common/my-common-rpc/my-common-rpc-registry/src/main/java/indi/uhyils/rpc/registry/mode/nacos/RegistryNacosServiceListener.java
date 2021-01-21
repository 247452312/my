package indi.uhyils.rpc.registry.mode.nacos;

import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import indi.uhyils.rpc.cluster.pojo.NettyInfo;
import indi.uhyils.rpc.registry.mode.AbstractRegistryServiceListener;
import indi.uhyils.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * todo测试完毕后将这里补全
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 18时49分
 */
public class RegistryNacosServiceListener extends AbstractRegistryServiceListener implements EventListener, Listener {

    @Override
    public void onEvent(Event event) {
        if (event != null) {
            String name = event.getClass().getName();
            LogUtil.info("name!!!!!!!!!!!!!!!!");
            LogUtil.info(name);
            if (event instanceof NamingEvent) {
                doServiceEvent((NamingEvent) event);
            }
        }
    }

    /**
     * service的event
     *
     * @param event
     */
    private void doServiceEvent(NamingEvent event) {
        List<Instance> instances = event.getInstances();
        ArrayList<NettyInfo> nettyInfos = new ArrayList<>();
        for (int i = 0; i < instances.size(); i++) {
            Instance instance = instances.get(i);
            if (!instance.isEnabled()) {
                continue;
            }
            NettyInfo nettyInfo = new NettyInfo();
            nettyInfo.setIndexInColony(i);
            nettyInfo.setHost(instance.getIp());
            nettyInfo.setPort(instance.getPort());
            nettyInfo.setWeight((int) instance.getWeight());
            nettyInfos.add(nettyInfo);
        }
        cluster.onServiceStatusChange(nettyInfos);
    }

    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
        LogUtil.info("configInfo!!!!!!!!!!!!!!!");
        LogUtil.info(configInfo);
    }
}
