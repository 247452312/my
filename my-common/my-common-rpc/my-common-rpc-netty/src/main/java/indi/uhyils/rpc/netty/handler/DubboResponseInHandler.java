package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactory;
import indi.uhyils.rpc.netty.pojo.RpcFactoryProducer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时58分
 */
public class DubboResponseInHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        ReferenceCountUtil.release(msg);

        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.RESPONSE);
        RpcData byBytes = build.createByBytes(bytes);
        RpcContent content = byBytes.content();
        String responseData = content.getLine(1);

        System.out.println(responseData);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // fixme 这里报错就不管了??
    }
}
