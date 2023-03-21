package indi.uhyils.rpc.registry.mode.nacos;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.pojo.NettyInfo;
import indi.uhyils.rpc.registry.mode.AbstractRegistryServiceListener;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;
import indi.uhyils.rpc.registry.pojo.info.RegistryProviderNecessaryInfo;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;

/**
 * nacos注册中心监听器
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 18时49分
 */
public class RegistryNacosServiceListener extends AbstractRegistryServiceListener implements EventListener, Listener {

    /**
     * 接口名称
     */
    private String interfaceName;


    /**
     * 验证用注册中心
     */
    private RegistryMode mode;


    public RegistryNacosServiceListener(RegistryMode mode, String interfaceName) {
        this.interfaceName = interfaceName;
        this.mode = mode;
    }

    @Override
    public void onEvent(Event event) {
        if (event == null) {
            return;
        }
        LogUtil.info("---------------------------------------------------------------------------------------------------------name!!!!!!!!!!!!!!!!");
        LogUtil.info(event.getClass().getName());
        LogUtil.info(JSON.toJSONString(event));
        if (event instanceof NamingEvent) {
            try {
                doServiceEvent((NamingEvent) event);
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
        } else {
            LogUtil.error("注册中心事件未响应" + event.getClass().getName());
        }
    }

    /**
     * service的event
     *
     * @param event
     */
    private void doServiceEvent(NamingEvent event) {
        // key-> 集群名称 value -> netty信息
        Map<String, List<NettyInfo>> nettyInfos = new HashMap<>();
        List<Instance> instances = event.getInstances();
        if (instances.isEmpty()) {
            verificationService(nettyInfos);
            return;
        }
        for (int i = 0; i < instances.size(); i++) {
            Instance instance = instances.get(i);
            if (!instance.isEnabled() || !instance.isHealthy()) {
                continue;
            }
            NettyInfo nettyInfo = new NettyInfo();
            nettyInfo.setIndexInColony(i);
            nettyInfo.setHost(instance.getIp());
            nettyInfo.setPort(instance.getPort());
            nettyInfo.setWeight((int) instance.getWeight());
            String clusterName = instance.getClusterName();
            if (!nettyInfos.containsKey(clusterName)) {
                nettyInfos.put(clusterName, new ArrayList<>());
            }
            nettyInfos.get(clusterName).add(nettyInfo);
        }
        for (Entry<String, List<NettyInfo>> entry : nettyInfos.entrySet()) {
            String clusterName = entry.getKey();
            List<NettyInfo> value = entry.getValue();
            if (cluster.containsKey(clusterName)) {
                cluster.get(clusterName).onServiceStatusChange(value);
            }
        }
        int i = 1;
    }

    private void verificationService(Map<String, List<NettyInfo>> nettyInfos) {
        try {
            Map<String, List<RegistryInfo>> targetInterfaceInfo = mode.getTargetInterfaceInfo(interfaceName);
            for (Entry<String, List<RegistryInfo>> entry : targetInterfaceInfo.entrySet()) {
                String key = entry.getKey();
                List<RegistryInfo> value = entry.getValue();
                List<NettyInfo> nettyInfoValue = new ArrayList<>();
                for (int i = 0; i < value.size(); i++) {
                    RegistryInfo registryInfo = value.get(i);
                    NettyInfo nettyInfo = new NettyInfo();
                    RegistryProviderNecessaryInfo necessaryInfo = (RegistryProviderNecessaryInfo) registryInfo.getNecessaryInfo();
                    nettyInfo.setIndexInColony(i);
                    nettyInfo.setHost(necessaryInfo.getHost());
                    nettyInfo.setPort(necessaryInfo.getPort());
                    double weight = necessaryInfo.getWeight();
                    nettyInfo.setWeight((int) weight);
                    nettyInfoValue.add(nettyInfo);
                }
                nettyInfos.put(key, nettyInfoValue);
                if (cluster.containsKey(key)) {
                    Cluster cluster = this.cluster.get(key);
                    cluster.onServiceStatusChange(nettyInfoValue);
                }
            }
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
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
