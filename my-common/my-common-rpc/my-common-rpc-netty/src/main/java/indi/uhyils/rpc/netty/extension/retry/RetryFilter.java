package indi.uhyils.rpc.netty.extension.retry;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.exchange.pojo.RpcFactoryProducer;
import indi.uhyils.rpc.exchange.pojo.request.RpcRequestFactory;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.filter.filter.ConsumerFilter;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResult;
import indi.uhyils.util.LogUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月23日 09时10分
 */
@RpcSpi(order = Integer.MIN_VALUE)
public class RetryFilter implements ConsumerFilter {
    @Override
    public RpcResult invoke(RpcInvoker invoker, FilterContext invokerContext) {
        RpcResult rpcResult = invokerContext.getRpcResult();
        RpcConfig instance = RpcConfigFactory.getInstance();
        Integer retries = instance.getConsumer().getRetries();
        RpcData request = invokerContext.getRpcResult().get();
        Throwable th = null;
        // 如果出错 重试
        for (int i = 0; i < retries; i++) {
            try {
                RpcResult invoke = invoker.invoke(invokerContext);
                rpcResult.set(invoke.get());
                return invoke;
            } catch (RpcException | InterruptedException e) {
                LogUtil.error(this, e);
                th = e;
            } catch (ClassNotFoundException e) {
                LogUtil.error(this, e);
                return null;
            }
        }
        RpcRequestFactory build = (RpcRequestFactory) RpcFactoryProducer.build(RpcTypeEnum.REQUEST);
        assert build != null;
        RpcData rpcData = build.createRetriesError(request, th);
        rpcResult.set(rpcData);
        return invokerContext.getRpcResult();
    }
}
