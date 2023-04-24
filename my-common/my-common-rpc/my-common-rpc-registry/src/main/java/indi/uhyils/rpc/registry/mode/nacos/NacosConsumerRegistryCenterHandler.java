package indi.uhyils.rpc.registry.mode.nacos;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.cluster.pojo.NettyInfo;
import indi.uhyils.rpc.config.RegistryConfig;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.registry.content.RegistryContent;
import indi.uhyils.rpc.registry.mode.AbstractConsumerRegistryCenterHandler;
import indi.uhyils.rpc.registry.pojo.RegistryMetadata;
import indi.uhyils.rpc.registry.pojo.RegistryModelInfo;
import indi.uhyils.rpc.registry.pojo.RegistryProviderNecessaryInfo;
import indi.uhyils.rpc.registry.pojo.event.RegistryEvent;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 *
 * 消费者注册中心句柄 的nacos默认实现 此类为实现句柄的例子可以通过spi机制修改
 *
 * @date 文件创建日期 2023年04月23日 16时41分
 * @author uhyils <247452312@qq.com>
 */
@RpcSpi(single = false)
public class NacosConsumerRegistryCenterHandler extends AbstractConsumerRegistryCenterHandler implements EventListener, Listener {


    /**
     * 注册元数据时的key
     */
    private static final String METADATA = "metadata";


    /**
     * nacos的naming
     */
    private final NamingService nacosNaming;

    /**
     * 服务地址
     */
    private String serverAddr;

    /**
     * @throws NacosException
     */
    public NacosConsumerRegistryCenterHandler() {
        try {
            RpcConfig rpcConfig = RpcConfigFactory.getInstance();
            RegistryConfig registry = rpcConfig.getRegistry();
            this.serverAddr = registry.getHost() + ":" + registry.getPort();
            nacosNaming = NamingFactory.createNamingService(serverAddr);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public Executor getExecutor() {
        // 不使用自己的执行器
        return null;
    }


    @Override
    public void receiveConfigInfo(String configInfo) {
        LogUtil.info("configInfo!!!!!!!!!!!!!!!");
        LogUtil.info(configInfo);
    }

    @Override
    public void onEvent(Event event) {
        if (event == null) {
            return;
        }
        LogUtil.info("onEvent,name:{},msg:{}", event.getClass().getName(), JSON.toJSONString(event));
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
        List<Instance> instances = event.getInstances();
        RegistryEvent registryEvent = new RegistryEvent();
        // key-> 集群名称 value -> netty信息
        Map<String, List<NettyInfo>> nettyInfos = new HashMap<>(instances.size());
        // 处理新增和修改
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
        registryEvent.setRegistryNettyInfoMap(nettyInfos);
        onEvent(registryEvent);

    }

    @Override
    public void close() {
        try {
            nacosNaming.unsubscribe(serviceClass.getName(), this);
            nacosNaming.shutDown();
        } catch (NacosException e) {
            LogUtil.error(this, e);
            throw new RpcException(e);
        }
    }

    @Override
    protected void initRegistryInfo() {
        List<Instance> allInstances;
        try {
            allInstances = nacosNaming.getAllInstances(serviceClass.getName(), RegistryContent.DEFAULT_REGISTRY_GROUP_NAME);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
        List<RegistryModelInfo> registryModels = new ArrayList<>();
        for (Instance instance : allInstances) {
            // 如果已注册 但是不对外提供服务的,排除掉
            if (!instance.isEnabled()) {
                continue;
            }
            String clusterName = instance.getClusterName();
            RegistryProviderNecessaryInfo providerNecessaryInfo = RegistryProviderNecessaryInfo.build(instance.getServiceName(), instance.getIp(), instance.getPort(), null, instance.isHealthy(), clusterName, instance.getWeight(), instance.isEnabled());
            Map<String, String> metadata = instance.getMetadata();
            RegistryMetadata registryMetadata = JSON.parseObject(metadata.get(METADATA), RegistryMetadata.class);

            RegistryModelInfo registryInfo = RegistryModelInfo.build(providerNecessaryInfo, registryMetadata);
            registryModels.add(registryInfo);
        }

        this.registryModelInfo = registryModels;
    }

    @Override
    protected void addConsumerListener() {
        try {
            nacosNaming.subscribe(serviceClass.getName(), this);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }
}
