package indi.uhyils.rpc.netty.extension.timeout;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.config.RpcConfigWarehouse;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.exchange.pojo.RpcFactoryProducer;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.filter.filter.ProviderFilter;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResult;
import indi.uhyils.util.LogUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月22日 21时11分
 */
@RpcSpi(order = Integer.MAX_VALUE)
public class ProviderTimeOutFilter extends TimeOutFilter implements ProviderFilter {

    public ProviderTimeOutFilter() {
        LogUtil.info("ProviderTimeOutFilter");
    }

    @Override
    public RpcResult invoke(RpcInvoker invoker, FilterContext invokerContext) throws RpcException, ClassNotFoundException, InterruptedException {
        return invoke0(invoker, invokerContext);
    }

    @Override
    protected RpcData invokeException(RpcData request, Long timeout) throws RpcException {
        return RpcFactoryProducer.build(RpcTypeEnum.RESPONSE).createTimeoutResponse(request, timeout);
    }

    @Override
    protected Long getTimeout() {
        return RpcConfigWarehouse.getRpcConfig().getProvider().getTimeout();
    }
}
