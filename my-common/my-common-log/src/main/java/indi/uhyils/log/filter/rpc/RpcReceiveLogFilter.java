package indi.uhyils.log.filter.rpc;

import com.alibaba.fastjson.JSON;
import indi.uhyils.log.MyTraceIdContext;
import indi.uhyils.log.RpcTraceInfo;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.data.AbstractRpcData;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.head.RpcHeader;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.filter.ProviderFilter;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;
import java.util.List;


/**
 * rpcTrace信息接收
 * 注: order排在timeOutFilter后面
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月23日 14时53分
 */
@RpcSpi(order = 101)
public class RpcReceiveLogFilter implements ProviderFilter {

    @Override
    public RpcData invoke(RpcInvoker invoker, FilterContext invokerContext) throws RpcException, ClassNotFoundException, InterruptedException {
        AbstractRpcData requestData = (AbstractRpcData) invokerContext.getRequestData();
        RpcHeader traceInfo = requestData.getHeader(MyTraceIdContext.RPC_HEADER_TRACE_INFO);
        if (traceInfo != null) {
            RpcTraceInfo rpcTraceInfo = JSON.parseObject(traceInfo.getValue(), RpcTraceInfo.class);
            List<Integer> rpcIds = rpcTraceInfo.getRpcIds();
            MyTraceIdContext.setRpcId(rpcIds);
            MyTraceIdContext.setThraceId(rpcTraceInfo.getTraceId());
        }
        try {
            return invoker.invoke(invokerContext);
        } finally {
            MyTraceIdContext.clean();
        }
    }

}
