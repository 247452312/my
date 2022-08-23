package indi.uhyils.protocol.mysql.netty.impl.other;

import indi.uhyils.mysql.decode.MysqlDecoder;
import indi.uhyils.mysql.util.MysqlUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月23日 15时03分
 */
public class MysqlTestDecoder extends ByteToMessageDecoder implements MysqlDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        byte[] packet = MysqlUtil.readPacket(in);
        if (packet == null) {
            return;
        }
        out.add(packet);
    }
}
