package indi.uhyils.rpc.registry.mode.nacos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.config.RegistryConfig;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.registry.content.RegistryContent;
import indi.uhyils.rpc.registry.exception.RegistryTypeException;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.rpc.registry.mode.RegistryServiceListener;
import indi.uhyils.rpc.registry.pojo.info.RegistryConsumerNecessaryInfo;
import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;
import indi.uhyils.rpc.registry.pojo.info.RegistryNecessaryInterface;
import indi.uhyils.rpc.registry.pojo.info.RegistryProviderNecessaryInfo;
import indi.uhyils.rpc.registry.pojo.info.metadata.RegistryMetadata;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * 默认的注册中心,如果有其他的.请自行实现
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 18时44分
 */
@RpcSpi(single = false)
public class RegistryNacosMode implements RegistryMode {

    /**
     * 注册元数据时的key
     */
    private static final String METADATA = "metadata";

    private static final Long TIME_OUT = 3000L;

    /**
     * nacos的Config
     */
    private final ConfigService nacosConfig;

    /**
     * nacos的naming
     */
    private final NamingService nacosNaming;

    /**
     * 此nacos连接的类型
     */
    private RpcNettyTypeEnum type;

    /**
     * 此服务在注册中心的数据
     */
    private RegistryInfo info;

    /**
     * 服务地址
     */
    private String serverAddr;


    /**
     * @throws NacosException
     */
    public RegistryNacosMode() {
        try {
            RpcConfig rpcConfig = RpcConfigFactory.getInstance();
            RegistryConfig registry = rpcConfig.getRegistry();
            this.serverAddr = registry.getHost() + ":" + registry.getPort();
            nacosConfig = ConfigFactory.createConfigService(serverAddr);
            nacosNaming = NamingFactory.createNamingService(serverAddr);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public String getConfig(String interfaceName) {
        try {
            return nacosConfig.getConfig(interfaceName, RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, TIME_OUT);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public Boolean publishConfig(String interfaceName, String content) {
        try {
            return nacosConfig.publishConfig(interfaceName, RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, content);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public Boolean removeConfig(String interfaceName) {
        try {
            return nacosConfig.removeConfig(interfaceName, RegistryContent.DEFAULT_REGISTRY_GROUP_NAME);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public void addConfigListener(String interfaceName, final RegistryServiceListener listener) {
        try {
            nacosConfig.addListener(interfaceName, RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, (RegistryNacosServiceListener) listener);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public void removeConfigListener(String interfaceName, RegistryServiceListener listener) {
        nacosConfig.removeListener(interfaceName, RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, (RegistryNacosServiceListener) listener);
    }

    @Override
    public Map<String, List<RegistryInfo>> getTargetInterfaceInfo(String interfaceName) {
        List<Instance> allInstances = null;
        try {
            allInstances = nacosNaming.getAllInstances(interfaceName, RegistryContent.DEFAULT_REGISTRY_GROUP_NAME);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
        Map<String, List<RegistryInfo>> stringRegistryInfoHashMap = new HashMap<>(allInstances.size());
        for (Instance instance : allInstances) {
            // 如果已注册 但是不对外提供服务的,排除掉
            if (!instance.isEnabled()) {
                continue;
            }
            String clusterName = instance.getClusterName();
            RegistryProviderNecessaryInfo providerNecessaryInfo = RegistryProviderNecessaryInfo.build(instance.getServiceName(), instance.getIp(), instance.getPort(), null, instance.isHealthy(), clusterName, instance.getWeight(), instance.isEnabled());
            Map<String, String> metadata = instance.getMetadata();
            RegistryMetadata registryMetadata = JSON.parseObject(metadata.get(METADATA), RegistryMetadata.class);

            RegistryInfo registryInfo = new RegistryInfo();
            registryInfo.setNecessaryInfo(providerNecessaryInfo);
            registryInfo.setMetadata(registryMetadata);
            if (!stringRegistryInfoHashMap.containsKey(clusterName)) {
                stringRegistryInfoHashMap.put(clusterName, new ArrayList<>());
            }
            stringRegistryInfoHashMap.get(clusterName).add(registryInfo);
        }

        return stringRegistryInfoHashMap;
    }

    @Override
    public Boolean registry(RegistryInfo info) {

        this.info = info;
        RegistryNecessaryInterface necessaryInfo = info.getNecessaryInfo();
        if (necessaryInfo instanceof RegistryConsumerNecessaryInfo) {
            throw new RegistryTypeException();
        }
        RegistryProviderNecessaryInfo providerNecessaryInfo = (RegistryProviderNecessaryInfo) necessaryInfo;

        //服务端数据转为注册中心数据
        Instance instance = registryInfoToInstance(info, providerNecessaryInfo);

        try {
            nacosNaming.registerInstance(providerNecessaryInfo.getInterfaceName(), RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, instance);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
        return Boolean.TRUE;
    }

    /**
     * 服务端数据转为注册中心数据
     *
     * @param info
     * @param providerNecessaryInfo
     *
     * @return
     */
    @NotNull
    private Instance registryInfoToInstance(RegistryInfo info, RegistryProviderNecessaryInfo providerNecessaryInfo) {
        Instance instance = new Instance();
        instance.setIp(providerNecessaryInfo.getHost());
        instance.setPort(providerNecessaryInfo.getPort());
        instance.setHealthy(providerNecessaryInfo.getHealth());
        instance.setWeight(providerNecessaryInfo.getWeight());
        instance.setServiceName(providerNecessaryInfo.getInterfaceName());
        instance.setClusterName(providerNecessaryInfo.getClusterName());
        instance.setEnabled(providerNecessaryInfo.getEnable());

        Map<String, String> instanceMeta = new HashMap<>(2);
        RegistryMetadata metadata = info.getMetadata();

        instanceMeta.put(METADATA, JSON.toJSONString(metadata, SerializerFeature.WriteClassName));
        instance.setMetadata(instanceMeta);
        return instance;
    }

    @Override
    public Boolean allowToPublish() {
        final RegistryProviderNecessaryInfo providerNecessaryInfo = (RegistryProviderNecessaryInfo) this.info.getNecessaryInfo();
        providerNecessaryInfo.setEnable(true);

        //服务端数据转为注册中心数据
        Instance instance = registryInfoToInstance(info, providerNecessaryInfo);

        try {
            nacosNaming.registerInstance(providerNecessaryInfo.getInterfaceName(), RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, instance);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean notAllowToPublish() {
        final RegistryProviderNecessaryInfo providerNecessaryInfo = (RegistryProviderNecessaryInfo) this.info.getNecessaryInfo();
        providerNecessaryInfo.setEnable(false);
        //服务端数据转为注册中心数据
        Instance instance = registryInfoToInstance(info, providerNecessaryInfo);

        try {
            nacosNaming.registerInstance(providerNecessaryInfo.getInterfaceName(), RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, instance);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
        return Boolean.TRUE;
    }

    @Override
    public void removeInstance() {
        final RegistryProviderNecessaryInfo providerNecessaryInfo = (RegistryProviderNecessaryInfo) this.info.getNecessaryInfo();

        try {
            nacosNaming.deregisterInstance(providerNecessaryInfo.getInterfaceName(), providerNecessaryInfo.getHost(), providerNecessaryInfo.getPort());
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public void addServiceListener(String interfaceName, String groupName, RegistryServiceListener listener) {
        try {
            nacosNaming.subscribe(interfaceName, groupName, (RegistryNacosServiceListener) listener);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public void removeServiceListener(String interfaceName, RegistryServiceListener listener) {
        try {
            nacosNaming.unsubscribe(interfaceName, (RegistryNacosServiceListener) listener);
        } catch (NacosException e) {
            throw new RpcException(e);
        }
    }

    @Override
    public void setType(RpcNettyTypeEnum type) {
        this.type = type;
    }

    @Override
    public void createListener(String interfaceName, Map<String, Cluster> cluster) {
        RegistryServiceListener listener = new RegistryNacosServiceListener(this, interfaceName);
        listener.setCluster(cluster);
        this.addServiceListener(interfaceName, RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, listener);
    }

    @Override
    public Boolean isPublish() {
        final RegistryProviderNecessaryInfo providerNecessaryInfo = (RegistryProviderNecessaryInfo) this.info.getNecessaryInfo();
        return providerNecessaryInfo.getEnable();
    }
}
