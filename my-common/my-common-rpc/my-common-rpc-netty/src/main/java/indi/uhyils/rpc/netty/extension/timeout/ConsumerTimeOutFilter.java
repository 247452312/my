package indi.uhyils.rpc.netty.extension.timeout;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.config.RpcConfigWarehouse;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.exchange.pojo.RpcFactoryProducer;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.filter.filter.ConsumerFilter;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResult;
import indi.uhyils.util.LogUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月21日 20时59分
 */
@RpcSpi(order = Integer.MAX_VALUE)
public class ConsumerTimeOutFilter extends TimeOutFilter implements ConsumerFilter {
    public ConsumerTimeOutFilter() {
        LogUtil.info("ConsumerTimeOutFilter");
    }

    @Override
    public RpcResult invoke(final RpcInvoker invoker, final FilterContext invokerContext) throws RpcException, ClassNotFoundException {
        return invoke0(invoker, invokerContext);
    }

    @Override
    protected RpcData invokeException(RpcData request, Long timeout) throws RpcException {
        return RpcFactoryProducer.build(RpcTypeEnum.REQUEST).createTimeoutResponse(request, timeout);
    }

    @Override
    protected Long getTimeout() {
        return RpcConfigWarehouse.getRpcConfig().getConsumer().getTimeout();
    }
}
