package indi.uhyils.rpc.netty.extension.timeout;

import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.config.RpcConfig;
import indi.uhyils.rpc.config.RpcConfigWarehouse;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.filter.filter.ConsumerRequestFilter;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResult;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResultImpl;
import indi.uhyils.util.LogUtil;

import java.util.concurrent.*;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月21日 20时59分
 */
@RpcSpi(order = Integer.MAX_VALUE)
public class ConsumerTimeOutFilter extends ConsumerRequestFilter {

    private static Executor executor = new ThreadPoolExecutor(5, 100, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
    private static ExecutorService es = new ThreadPoolExecutor(5, 100, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

    @Override
    public RpcResult invoke(final RpcInvoker invoker, final FilterContext invokerContext) throws RpcException, ClassNotFoundException {
        RpcConfig rpcConfig = RpcConfigWarehouse.getRpcConfig();
        final Long timeOut = rpcConfig.getConsumer().getTimeOut();
        Future<RpcResult> submit = es.submit(() -> invoker.invoke(invokerContext));
        Runnable runnable = () -> {
            try {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < timeOut) {
                    Thread.sleep(100);
                    if (!submit.isCancelled()) {
                        return;
                    }
                }
                if (submit.isCancelled()) {
                    submit.cancel(true);
                }
            } catch (InterruptedException e) {
                LogUtil.error(this, e);
            }
        };

        executor.execute(runnable);
        try {
            return submit.get();
        } catch (InterruptedException | ExecutionException e) {
            return new RpcResultImpl();
        }

    }
}
