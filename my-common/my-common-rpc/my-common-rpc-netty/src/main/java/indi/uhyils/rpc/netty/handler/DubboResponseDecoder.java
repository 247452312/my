package indi.uhyils.rpc.netty.handler;

import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactory;
import indi.uhyils.rpc.netty.pojo.RpcFactoryProducer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 07时16分
 */
public class DubboResponseDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.RESPONSE);
        RpcData rpcData = build.createByByteBuf(in);
        if (rpcData == null) {
            return;
        }
        out.add(rpcData);
    }
}
