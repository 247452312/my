package indi.uhyils.rpc.filter.ip;

import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exchange.pojo.data.AbstractRpcData;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.head.RpcHeader;
import indi.uhyils.rpc.exchange.pojo.head.RpcHeaderFactory;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.filter.ConsumerFilter;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;
import indi.uhyils.util.CollectionUtil;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月20日 11时08分
 */
@RpcSpi
public class IpConsumerFilter implements ConsumerFilter {


    @Override
    public RpcData invoke(RpcInvoker invoker, FilterContext invokerContext) throws InterruptedException {
        Optional<String> ipOpt = UserInfoHelper.getUserIp();
        ipOpt.ifPresent(ip -> {
            final AbstractRpcData requestData = (AbstractRpcData) invokerContext.getRequestData();
            RpcHeader[] rpcHeaders = requestData.rpcHeaders();
            rpcHeaders = CollectionUtil.arrayAdd(rpcHeaders, RpcHeaderFactory.newHeader(UserInfoHelper.USER_IP_RPC_KEY, ip));
            requestData.setHeaders(rpcHeaders);
        });
        return invoker.invoke(invokerContext);
    }
}
