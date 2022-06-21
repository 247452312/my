package indi.uhyils.rpc.filter;

import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.data.AbstractRpcData;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.head.RpcHeader;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.filter.ProviderFilter;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月20日 11时13分
 */
@RpcSpi
public class IpProviderFilter implements ProviderFilter {

    @Override
    public RpcData invoke(RpcInvoker invoker, FilterContext invokerContext) throws RpcException, ClassNotFoundException, InterruptedException {
        final AbstractRpcData requestData = (AbstractRpcData) invokerContext.getRequestData();
        final RpcHeader header = requestData.getHeader(UserInfoHelper.USER_IP_RPC_KEY);

        try {
            if (header != null) {
                UserInfoHelper.setIp(header.getValue());
            }
            return invoker.invoke(invokerContext);
        } finally {
            UserInfoHelper.cleanIp();
        }
    }
}
