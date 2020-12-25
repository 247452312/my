package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时58分
 */
public class DubboRequestInHandler extends SimpleChannelInboundHandler<ByteBuf> {


    /**
     * 回调
     */
    private final RpcCallBack callback;

    public DubboRequestInHandler(RpcCallBack callback) {
        this.callback = callback;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        /*接收并释放byteBuf*/
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        ReferenceCountUtil.release(msg);
        RpcContent content = callback.getContent(bytes);
        String resultJson = callback.invoke(content);
        System.out.println("--------------------------------------------------" + resultJson);
        RpcData assembly = callback.assembly(resultJson);

        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(assembly.toBytes());
        ctx.channel().writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // fixme 这里报错就不管了??
    }
}
