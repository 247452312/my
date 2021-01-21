package indi.uhyils.rpc.registry;

import com.alibaba.fastjson.JSON;
import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.cluster.pojo.SendInfo;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.exchange.pojo.RpcFactory;
import indi.uhyils.rpc.exchange.pojo.RpcFactoryProducer;
import indi.uhyils.rpc.exchange.pojo.RpcHeader;
import indi.uhyils.rpc.exchange.pojo.response.RpcNormalResponseContent;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.registry.content.RegistryContent;
import indi.uhyils.rpc.registry.mode.RegistryMode;
import indi.uhyils.rpc.registry.mode.nacos.RegistryNacosServiceListener;
import indi.uhyils.util.LogUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时43分
 */
public class ConsumerRegistry<T> extends AbstractRegistry<T> {

    /**
     * 发送者的ip
     */
    private String selfIp;

    public ConsumerRegistry(Cluster cluster, Class<T> serviceClass, String selfIp, RegistryMode mode) {
        super(cluster, serviceClass);
        this.selfIp = selfIp;
        this.mode = mode;
        mode.setType(RpcNettyTypeEnum.CONSUMER);
        try {
            RegistryNacosServiceListener listener = new RegistryNacosServiceListener();
            listener.setCluster(cluster);
            mode.addServiceListener(serviceClass.getName(), RegistryContent.DEFAULT_REGISTRY_GROUP_NAME, listener);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
    }

    private String getConfigListenerName(Class<T> serviceClass) {
        StringBuilder sb = new StringBuilder();
        sb.append(serviceClass.getSimpleName());
        sb.append("ConfigListener");
        return sb.toString();
    }

    @Override
    public String invoke(Long unique, String methodName, Class[] paramType, Object[] args) throws RpcException, ClassNotFoundException, InterruptedException {
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.REQUEST);
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
        return content.getResponseContent();
    }
}
