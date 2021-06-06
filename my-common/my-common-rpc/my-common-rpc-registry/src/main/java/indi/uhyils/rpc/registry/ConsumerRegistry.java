package indi.uhyils.rpc.registry;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.exception.NacosException;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.ClusterFactory;
import indi.uhyils.rpc.cluster.pojo.SendInfo;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.enums.RpcResponseTypeEnum;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.exchange.pojo.RpcHeader;
import indi.uhyils.rpc.exchange.pojo.demo.response.content.RpcNormalResponseContent;
import indi.uhyils.rpc.exchange.pojo.factory.RpcFactory;
import indi.uhyils.rpc.exchange.pojo.factory.RpcFactoryProducer;
import indi.uhyils.rpc.netty.callback.impl.RpcDefaultResponseCallBack;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.netty.factory.NettyInitDtoFactory;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;
import indi.uhyils.rpc.registry.content.RegistryContent;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.rpc.registry.mode.nacos.RegistryNacosServiceListener;
import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;
import indi.uhyils.rpc.registry.pojo.info.RegistryProviderNecessaryInfo;
import indi.uhyils.rpc.spi.RpcSpiManager;
import indi.uhyils.util.IpUtil;
import indi.uhyils.util.LogUtil;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时43分
 */
@RpcSpi(single = false)
public class ConsumerRegistry<T> extends AbstractRegistry<T> {
    /**
     * 默认的注册中心
     */
    private static final String DEFAULT_MODE_REGISTRY = "default_mode_registry";
    /**
     * 配置中注册中心的名称
     */
    private static final String REGISTRY_MODE_SPI_NAME = "registryModeSpi";

    /**
     * 发送者的ip
     */
    private String selfIp;

    /**
     * @param cluster      集群信息
     * @param serviceClass 目标的类的class
     * @param mode         注册中心的信息
     */
    public ConsumerRegistry(Cluster cluster, Class<T> serviceClass, RegistryMode mode) {
        super(cluster, serviceClass);
        this.selfIp = IpUtil.getIp();
        this.mode = mode;
        mode.setType(RpcNettyTypeEnum.CONSUMER);
        try {
            RegistryNacosServiceListener listener = new RegistryNacosServiceListener(mode, cluster.getInterfaceName());
            listener.setCluster(cluster);
            mode.addServiceListener(serviceClass.getName(), RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, listener);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
    }

    public ConsumerRegistry() {
    }


    /**
     * 初始化consumer
     */
    @Override
    public void init(Object... objects) {
        // 解析入参,只有一个class
        Class clazz = (Class) objects[0];

        // spi 获取消费者的注册者信息
        String name = (String) RpcConfigFactory.getCustomOrDefault(REGISTRY_MODE_SPI_NAME, DEFAULT_MODE_REGISTRY);
        RegistryMode mode = (RegistryMode) RpcSpiManager.getExtensionByClass(RegistryMode.class, name);
        assert mode != null;

        mode.setType(RpcNettyTypeEnum.CONSUMER);
        this.mode = mode;

        try {
            Cluster cluster = createCluster();
            super.init(cluster, clazz);
            this.selfIp = IpUtil.getIp();

            // 创建监听器.监听注册中心
            mode.createListener(clazz.getName(), cluster);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
    }

    private Cluster createCluster() throws NacosException {

        /*构建netty初始化需要的信息*/
        List<RegistryInfo> remoteInfo = this.mode.getTargetInterfaceInfo(this.serviceClass.getName());
        // 获取目标接口的信息
        NettyInitDto[] nettyInits = new NettyInitDto[remoteInfo.size()];
        for (int i = 0; i < remoteInfo.size(); i++) {
            RegistryInfo registryInfo = remoteInfo.get(i);
            //查询到目标class注册到注册中心的信息
            RegistryProviderNecessaryInfo necessaryInfo = (RegistryProviderNecessaryInfo) registryInfo.getNecessaryInfo();
            nettyInits[i] = NettyInitDtoFactory.createNettyInitDto(necessaryInfo.getHost(), necessaryInfo.getPort(), necessaryInfo.getWeight().intValue(), new RpcDefaultResponseCallBack());
        }

        return ClusterFactory.createDefaultConsumerCluster(this.serviceClass, nettyInits);
    }


    @Override
    public String invoke(Long unique, String methodName, Class[] paramType, Object[] args) throws RpcException, ClassNotFoundException, InterruptedException {
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.REQUEST);
        // header具体发送什么还没有确定
        RpcHeader rpcHeader = new RpcHeader();
        rpcHeader.setName("default_value");
        rpcHeader.setValue("value");
        assert build != null;
        StringBuilder sb = new StringBuilder();
        for (Class<?> paramTypeClass : paramType) {
            sb.append(paramTypeClass.getName());
            sb.append(";");
        }
        sb.delete(sb.length() - 1, sb.length());
        RpcData rpcData = build.createByInfo(unique, null, new RpcHeader[]{rpcHeader}, serviceClass.getName(), "1", methodName, sb.toString(), JSON.toJSONString(args), "[]");

        SendInfo info = new SendInfo();
        info.setIp(selfIp);

        RpcData rpcResponseData = cluster.sendMsg(rpcData, info);
        RpcNormalResponseContent content = (RpcNormalResponseContent) rpcResponseData.content();
        Integer responseType = content.responseType();
        RpcResponseTypeEnum type = RpcResponseTypeEnum.parse(responseType);
        if (type == RpcResponseTypeEnum.EXCEPTION) {
            throw new RpcException("请求出错:" + content.getResponseContent());
        } else if (type == RpcResponseTypeEnum.NULL_BACK) {
            return null;
        }
        return content.getResponseContent();
    }
}
