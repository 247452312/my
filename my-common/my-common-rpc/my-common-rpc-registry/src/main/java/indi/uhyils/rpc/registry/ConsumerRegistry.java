package indi.uhyils.rpc.registry;

import com.alibaba.fastjson.JSON;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.pojo.SendInfo;
import indi.uhyils.rpc.enums.RpcResponseTypeEnum;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.exchange.pojo.RpcHeader;
import indi.uhyils.rpc.exchange.pojo.demo.response.content.RpcNormalResponseContent;
import indi.uhyils.rpc.exchange.pojo.factory.RpcFactory;
import indi.uhyils.rpc.exchange.pojo.factory.RpcFactoryProducer;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.registry.content.RegistryContent;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.rpc.registry.mode.nacos.RegistryNacosServiceListener;
import indi.uhyils.util.IpUtil;
import indi.uhyils.util.LogUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时43分
 */
@RpcSpi(single = false)
public class ConsumerRegistry<T> extends AbstractRegistry<T> {

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
     * 构造
     *
     * @param cluster      集群信息
     * @param serviceClass 目标的类的class
     * @param mode         注册中心的信息
     * @return
     */
    public static <T> ConsumerRegistry<T> build(Cluster cluster, Class<T> serviceClass, RegistryMode mode) {
        ConsumerRegistry<T> build = new ConsumerRegistry<>();
        build.init(cluster, serviceClass, mode);
        return build;

    }

    /**
     * 初始化consumer
     *
     * @param cluster
     * @param serviceClass
     * @param mode
     */
    public void init(Cluster cluster, Class<T> serviceClass, RegistryMode mode) {
        init(cluster, serviceClass);
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
        RpcData getHeader = build.createByInfo(unique, null, new RpcHeader[]{rpcHeader}, serviceClass.getName(), "1", methodName, sb.toString(), JSON.toJSONString(args), "[]");

        SendInfo info = new SendInfo();
        info.setIp(selfIp);

        RpcData rpcData = cluster.sendMsg(getHeader, info);
        RpcNormalResponseContent content = (RpcNormalResponseContent) rpcData.content();
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
