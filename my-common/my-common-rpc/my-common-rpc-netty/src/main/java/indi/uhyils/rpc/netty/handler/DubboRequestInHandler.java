package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.netty.callback.RpcRequestCallback;
import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactory;
import indi.uhyils.rpc.netty.pojo.RpcFactoryProducer;
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
    private final RpcRequestCallback callback;

    public DubboRequestInHandler(RpcRequestCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        /*接收并释放byteBuf*/
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        ReferenceCountUtil.release(msg);
        /*解析*/
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.REQUEST);
        // 获取到的Request
        assert build != null;
        RpcData request = build.createByBytes(bytes);
        Integer version = request.rpcVersion();
        if (version <= MyRpcContent.VERSION) {
            RpcData invoke = callback.invoke(request.content());
            ByteBuf buf = Unpooled.buffer();
            buf.writeBytes(invoke.toBytes());
            ctx.channel().writeAndFlush(buf);

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // fixme 这里报错就不管了??
    }
}
