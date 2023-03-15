package indi.uhyils.rpc.netty.spi.filter.invoker;

import indi.uhyils.rpc.exchange.pojo.content.RpcRequestContent;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.netty.core.RpcSelfNetty;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.util.LogUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月21日 21时07分
 */
public class LastSelfInvoker implements RpcInvoker {

    /**
     * netty
     */
    private RpcSelfNetty<?> netty;

    private Object service;

    public LastSelfInvoker(RpcSelfNetty<?> netty) {
        this.netty = netty;
        this.service = netty.service();
    }

    @Override
    public RpcData invoke(FilterContext context) throws InterruptedException {
        RpcData request = context.getRequestData();
        final RpcRequestContent content = (RpcRequestContent) request.content();
        LogUtil.info("调用本项目的服务:{},{}", content.getServiceName(), content.getMethodName());
        final byte[] invoke = netty.invoke(request.toBytes());
        return netty.parseInvoke(invoke);
    }
}
