package indi.uhyils.rpc.netty.spi.filter.timeout;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.data.factory.NormalRpcRequestFactory;
import indi.uhyils.rpc.exchange.pojo.data.factory.RpcFactoryProducer;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.filter.ConsumerFilter;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;
import java.util.concurrent.ExecutionException;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月21日 20时59分
 */
@RpcSpi(order = 100)
public class ConsumerTimeOutFilter extends AbstractTimeOutFilter implements ConsumerFilter {


    @Override
    public RpcData invoke(final RpcInvoker invoker, final FilterContext invokerContext) throws InterruptedException {
        return invoke0(invoker, invokerContext);
    }

    @Override
    protected RpcData invokeException(RpcData request, Long timeout) throws InterruptedException {
        return RpcFactoryProducer.build(RpcTypeEnum.REQUEST).createTimeoutResponse(request, timeout);
    }

    @Override
    protected RpcData invokeException(RpcData request, Throwable e) throws InterruptedException {
        NormalRpcRequestFactory build = (NormalRpcRequestFactory) RpcFactoryProducer.build(RpcTypeEnum.REQUEST);
        return build.createExceptionResponse(request, e);
    }

    @Override
    protected Long getTimeout() {
        return RpcConfigFactory.getInstance().getConsumer().getTimeout();
    }
}
