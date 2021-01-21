package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.core.RpcNettyNormalConsumer;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.filter.filter.InvokerChainBuilder;
import indi.uhyils.rpc.netty.extension.filter.invoker.LastConsumerResponseInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResult;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时58分
 */
public class RpcConsumerHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /**
     * 回调
     */
    private RpcCallBack callBack;
    /**
     * 观察者模式
     */
    private RpcNettyNormalConsumer netty;


    public RpcConsumerHandler(RpcCallBack callBack, RpcNettyNormalConsumer netty) {
        this.callBack = callBack;
        this.netty = netty;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        LastConsumerResponseInvoker invoker = new LastConsumerResponseInvoker(callBack, ctx, msg);
        RpcInvoker rpcInvoker = InvokerChainBuilder.buildConsumerResponseInvokerChain(invoker);
        RpcResult invoke = rpcInvoker.invoke(new FilterContext());
        netty.put((RpcData) invoke.get());
    }
}
