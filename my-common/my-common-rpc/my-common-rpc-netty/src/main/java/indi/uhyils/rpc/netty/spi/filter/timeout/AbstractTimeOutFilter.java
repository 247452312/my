package indi.uhyils.rpc.netty.spi.filter.timeout;

import indi.uhyils.MyExecutorWrapper;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.content.RpcRequestContent;
import indi.uhyils.rpc.exchange.pojo.data.AbstractRequestRpcData;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;
import indi.uhyils.util.LogUtil;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月22日 21时14分
 */
public abstract class AbstractTimeOutFilter {

    private static final ExecutorService es = MyExecutorWrapper.createByThreadPoolExecutor(new ThreadPoolExecutor(5, 100, 3000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10)));

    protected RpcData invoke0(RpcInvoker invoker, FilterContext invokerContext) throws InterruptedException {
        RpcData requestData = invokerContext.getRequestData();
        Long timeOut = getTimeout();
        Callable<RpcData> rpcDataCallable = () -> {
            try {
                return invoker.invoke(invokerContext);
            } catch (RpcException e) {
                LogUtil.error(e);
                throw e;
            }

        };
        Future<RpcData> submit = es.submit(rpcDataCallable);
        try {
            RpcData rpcData = submit.get(timeOut, TimeUnit.MILLISECONDS);
            return rpcData;
        } catch (TimeoutException e) {
            AbstractRequestRpcData abstractRequestRpcData = (AbstractRequestRpcData) requestData;
            RpcRequestContent content = (RpcRequestContent) abstractRequestRpcData.getContent();
            LogUtil.error(e, "Rpc超时,接口:{},方法:{}", content.getServiceName(), content.getMethodName());
            submit.cancel(Boolean.TRUE);
            return invokeException(requestData, timeOut);
        } catch (ExecutionException e) {
            LogUtil.error(e.getCause());
            submit.cancel(Boolean.TRUE);
            return invokeException(requestData, e.getCause());
        } catch (InterruptedException e) {
            throw e;
        }
    }

    /**
     * 超时时要做的事情
     *
     * @param request
     * @param timeout
     *
     * @return
     */
    protected abstract RpcData invokeException(RpcData request, Long timeout) throws InterruptedException;

    /**
     * 异常时要做的事
     *
     * @param request
     * @param e
     *
     * @return
     */
    protected abstract RpcData invokeException(RpcData request, Throwable e) throws InterruptedException;

    /**
     * 获取超时时间
     *
     * @return
     */
    protected abstract Long getTimeout();
}
