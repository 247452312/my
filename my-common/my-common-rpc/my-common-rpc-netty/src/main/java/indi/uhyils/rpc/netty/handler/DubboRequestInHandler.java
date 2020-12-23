package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcStatusEnum;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.pojo.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 10时58分
 */
public class DubboRequestInHandler extends SimpleChannelInboundHandler<ByteBuf> {


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
            RpcContent content = request.content();
            String execute = content.execute();

            RpcFactory responseFactory = RpcFactoryProducer.build(RpcTypeEnum.RESPONSE);
            RpcHeader rpcHeader = new RpcHeader();
            rpcHeader.setName("default-value");
            rpcHeader.setValue("value");
            RpcHeader[] rpcHeaders = {rpcHeader};
            assert responseFactory != null;
            String responseType;
            if (StringUtils.isEmpty(execute)) {
                responseType = "2";
            } else {
                responseType = "1";
            }
            RpcData byInfo = responseFactory.createByInfo(new Object[]{RpcStatusEnum.OK.getCode()}, rpcHeaders, responseType, execute);
            ByteBuf buf = Unpooled.buffer();
            buf.writeBytes(byInfo.toBytes());
            ctx.channel().writeAndFlush(buf);

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // fixme 这里报错就不管了??
    }
}
