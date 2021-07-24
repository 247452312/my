package indi.uhyils.log.filter.rpc;

import indi.uhyils.log.LogTypeEnum;
import indi.uhyils.log.MyTraceIdContext;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.enum_.RpcRequestContentEnum;
import indi.uhyils.rpc.exchange.pojo.content.RpcContent;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.filter.ConsumerFilter;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月23日 14时53分
 */
@RpcSpi
public class RpcLogFilter implements ConsumerFilter {

    @Override
    public RpcData invoke(RpcInvoker invoker, FilterContext invokerContext) throws RpcException, ClassNotFoundException, InterruptedException {
        long startTime = System.currentTimeMillis();
        RpcData invoke = invoker.invoke(invokerContext);
        RpcData requestData = invokerContext.getRequestData();
        long timeConsuming = System.currentTimeMillis() - startTime;
        RpcContent content = requestData.content();
        MyTraceIdContext
            .printLogInfo(LogTypeEnum.RPC, startTime, timeConsuming, content.getLine(RpcRequestContentEnum.SERVICE_NAME.getLine()), content.getLine(RpcRequestContentEnum.METHOD_NAME.getLine()));
        return invoke;
    }
}
