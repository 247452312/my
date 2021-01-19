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

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时43分
 */
public class ConsumerRegistry<T> extends AbstractRegistry<T> {

    /**
     * 发送者的ip
     */
    private String selfIp;

    public ConsumerRegistry(Cluster cluster, Class<T> serviceClass, String selfIp) {
        super(cluster, serviceClass);
        this.selfIp = selfIp;
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
