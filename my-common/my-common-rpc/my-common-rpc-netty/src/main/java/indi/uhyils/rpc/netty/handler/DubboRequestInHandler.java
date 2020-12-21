package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactory;
import indi.uhyils.rpc.netty.pojo.RpcFactoryProducer;
import indi.uhyils.rpc.netty.pojo.RpcHeader;
import indi.uhyils.rpc.netty.pojo.request.RpcNormalRequest;
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


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.REQUEST);
        // 获取到的Request
        RpcNormalRequest request = (RpcNormalRequest) build.createByBytes(bytes);
        Integer version = request.getVersion();
        if (version <= MyRpcContent.VERSION) {
            RpcFactory responseFactory = RpcFactoryProducer.build(RpcTypeEnum.RESPONSE);
            RpcHeader rpcHeader = new RpcHeader();
            rpcHeader.setName("name");
            rpcHeader.setValue("value");
            RpcHeader[] rpcHeaders = {rpcHeader};
            assert responseFactory != null;
            RpcData byInfo = responseFactory.createByInfo(MyRpcContent.VERSION, new Object[]{(byte) 10}, rpcHeaders, "1", "{\"code\":1,\"data\":true}");
            ByteBuf buf = null;
            try {
                buf = Unpooled.buffer();
                buf.writeBytes(byInfo.toBytes());
                ctx.channel().writeAndFlush(buf);
            } finally {
                if (buf != null) {
                    ReferenceCountUtil.release(buf);
                }
            }
        }
    }
}
