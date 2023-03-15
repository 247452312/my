package indi.uhyils.rpc.netty.spi.filter.retry;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.filter.ConsumerFilter;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;
import indi.uhyils.util.LogUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月23日 09时10分
 */
@RpcSpi(order = -50)
public class RetryFilter implements ConsumerFilter {

    @Override
    public RpcData invoke(RpcInvoker invoker, FilterContext invokerContext) throws InterruptedException {
        RpcConfig instance = RpcConfigFactory.getInstance();
        Integer retries = instance.getConsumer().getRetries();
        // 如果出错 重试 次数: 重试次数-1
        for (int i = 0; i < retries - 1; i++) {
            try {
                return invoker.invoke(invokerContext);
            } catch (RpcException e) {
                LogUtil.error("请求出错,等待请求第{}次", String.valueOf(i));
            }
        }
        return invoker.invoke(invokerContext);

    }
}
