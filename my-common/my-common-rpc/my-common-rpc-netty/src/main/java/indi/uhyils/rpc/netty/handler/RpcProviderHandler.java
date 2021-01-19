package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.extension.filter.FilterContext;
import indi.uhyils.rpc.netty.extension.filter.filter.InvokerChainBuilder;
import indi.uhyils.rpc.netty.extension.filter.invoker.LastDefaultProviderInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcInvoker;
import indi.uhyils.rpc.netty.extension.filter.invoker.RpcResult;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时58分
 */
public class RpcProviderHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /**
     * 回调
     */
    private final RpcCallBack callback;


    public RpcProviderHandler(RpcCallBack callback) {
        this.callback = callback;

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        LastDefaultProviderInvoker invoker = new LastDefaultProviderInvoker(callback, ctx, msg);
        RpcInvoker rpcInvoker = InvokerChainBuilder.buildProviderInvokerChain(invoker);
        RpcResult invoke = rpcInvoker.invoke(new FilterContext());
        send(ctx, (byte[]) invoke.get());
    }

    private void send(ChannelHandlerContext ctx, byte[] responseBytes) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(responseBytes);
        ctx.channel().writeAndFlush(buf);
    }

}
