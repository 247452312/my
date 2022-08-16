package indi.uhyils.mysql.decode.impl;

import indi.uhyils.mysql.decode.MysqlDecoder;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月22日 09时20分
 */
public class MysqlDecoderImpl extends ByteToMessageDecoder implements MysqlDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        byte[] packet = MysqlUtil.readPacket(in);
        if (packet == null) {
            return;
        }
        String dump = MysqlUtil.dump(packet);
        LogUtil.info("客户端发来请求:\n" + dump);
        out.add(packet);
    }
}
