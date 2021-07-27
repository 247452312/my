package indi.uhyils.log.filter.rpc;

import com.alibaba.fastjson.JSON;
import indi.uhyils.log.LogTypeEnum;
import indi.uhyils.log.MyTraceIdContext;
import indi.uhyils.log.RpcTraceInfo;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.enum_.RpcRequestContentEnum;
import indi.uhyils.rpc.exchange.pojo.content.RpcContent;
import indi.uhyils.rpc.exchange.pojo.data.AbstractRpcData;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.head.RpcHeader;
import indi.uhyils.rpc.exchange.pojo.head.RpcHeaderFactory;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.filter.ConsumerFilter;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;
import indi.uhyils.util.LogUtil;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月23日 14时53分
 */
@RpcSpi
public class RpcSendLogFilter implements ConsumerFilter {

    @Override
    public RpcData invoke(RpcInvoker invoker, FilterContext invokerContext) {
        // 优先获取rpcId,防止thisRpcId提前+1
        List<Integer> nextRpcIds = MyTraceIdContext.getNextRpcIds();
        AbstractRpcData requestData = (AbstractRpcData) invokerContext.getRequestData();
        RpcContent content = requestData.content();
        return MyTraceIdContext.printLogInfo(LogTypeEnum.RPC, () -> {
            try {
                addRpcTraceInfoToRpcHeader(requestData, nextRpcIds);
                return invoker.invoke(invokerContext);
            } catch (RpcException | ClassNotFoundException | InterruptedException e) {
                LogUtil.error(this, e);
            }
            return null;
        }, new String[]{content.getLine(RpcRequestContentEnum.SERVICE_NAME.getLine()),
            content.getLine(RpcRequestContentEnum.METHOD_NAME.getLine())});
    }

    private void addRpcTraceInfoToRpcHeader(AbstractRpcData requestData, List<Integer> nextRpcIds) {
        RpcTraceInfo rpcTraceInfo = RpcTraceInfo.build(MyTraceIdContext.getThraceId(), nextRpcIds);

        RpcHeader[] rpcHeaders = requestData.rpcHeaders();
        int rpcHeaderLength = rpcHeaders.length;
        RpcHeader[] newRpcHeaders = new RpcHeader[rpcHeaderLength + 1];
        System.arraycopy(rpcHeaders, 0, newRpcHeaders, 0, rpcHeaderLength);
        newRpcHeaders[rpcHeaderLength] = RpcHeaderFactory.newHeader(MyTraceIdContext.RPC_HEADER_TRACE_INFO, JSON.toJSONString(rpcTraceInfo));
        requestData.setHeaders(newRpcHeaders);
    }
}
