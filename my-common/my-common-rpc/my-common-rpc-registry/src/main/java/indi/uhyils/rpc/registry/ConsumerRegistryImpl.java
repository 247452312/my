package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.ClusterFactory;
import indi.uhyils.rpc.cluster.pojo.SendInfo;
import indi.uhyils.rpc.exception.RpcNetException;
import indi.uhyils.rpc.exception.RpcShowDownException;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.callback.RpcCallBackFactory;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.netty.factory.NettyInitDtoFactory;
import indi.uhyils.rpc.netty.pojo.NettyInitDto;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.rpc.registry.mode.RegistryModeFactory;
import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;
import indi.uhyils.rpc.registry.pojo.info.RegistryProviderNecessaryInfo;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.IpUtil;
import indi.uhyils.util.LogUtil;
import java.util.List;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时43分
 */
@RpcSpi(single = false)
public class ConsumerRegistryImpl<T> extends AbstractRegistry<T> implements ConsumerRegistry<T> {


    /**
     * 发送者的ip
     */
    private final String selfIp;

    public ConsumerRegistryImpl() {
        this.selfIp = IpUtil.getIp();
    }

    @Override
    public RpcData invoke(RpcData rpcData) throws InterruptedException {
        return cluster.sendMsg(rpcData, new SendInfo(selfIp));
    }

    @Override
    public void close() {

        final Boolean shutdown = cluster.shutdown();
        if (!shutdown) {
            throw new RpcShowDownException("rpc关闭错误");
        }
    }

    @Override
    protected void doRegistryInit(Object... objects) throws InterruptedException {
        RegistryMode mode = RegistryModeFactory.create();
        mode.setType(RpcNettyTypeEnum.CONSUMER);
        this.mode = mode;

        try {
            this.cluster = createCluster();
            // 创建监听器.监听注册中心
            mode.createListener(this.serviceClass.getName(), cluster);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
    }

    /**
     * 创建一个消费者的cluster,包含了目标的cluster
     *
     * @return
     */
    private Cluster createCluster() throws InterruptedException {

        /*构建netty初始化需要的信息*/
        List<RegistryInfo> remoteInfo = this.mode.getTargetInterfaceInfo(this.serviceClass.getName());
        if (CollectionUtil.isEmpty(remoteInfo)) {
            throw new RpcNetException("不存在指定的服务:" + this.serviceClass.getName());
        }
        // 获取目标接口的信息
        NettyInitDto[] nettyInits = new NettyInitDto[remoteInfo.size()];
        for (int i = 0; i < remoteInfo.size(); i++) {
            RegistryInfo registryInfo = remoteInfo.get(i);
            //查询到目标class注册到注册中心的信息
            RegistryProviderNecessaryInfo necessaryInfo = (RegistryProviderNecessaryInfo) registryInfo.getNecessaryInfo();

            // 是否允许调用自身
            if (Boolean.TRUE.equals(protocolConfig.getAutoUseSelf()) && isSelfService(necessaryInfo.getHost(), necessaryInfo.getPort())) {
                nettyInits[i] = NettyInitDtoFactory.createSelfNettyInitDto();
                continue;
            }
            nettyInits[i] = NettyInitDtoFactory.createNettyInitDto(necessaryInfo.getHost(), necessaryInfo.getPort(), necessaryInfo.getWeight().intValue(), RpcCallBackFactory.createResponseCallBack());
        }

        try {
            return ClusterFactory.createDefaultConsumerCluster(this.serviceClass, nettyInits);
        } catch (Exception e) {
            LogUtil.error(this, e);
            return null;
        }
    }

    /**
     * 判断是否是自己的服务
     *
     * @param host
     * @param port
     *
     * @return
     */
    private boolean isSelfService(String host, Integer port) {
        return Objects.equals(host, selfIp) && Objects.equals(selfPort, port);
    }


}
