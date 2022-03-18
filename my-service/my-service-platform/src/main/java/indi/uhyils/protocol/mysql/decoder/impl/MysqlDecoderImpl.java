package indi.uhyils.protocol.mysql.decoder.impl;

import indi.uhyils.MysqlUtil;
import indi.uhyils.protocol.mysql.decoder.MysqlDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;


/**
 * mysql协议解析类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时26分
 */
public class MysqlDecoderImpl extends ByteToMessageDecoder implements MysqlDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        byte[] packet = MysqlUtil.readPacket(in);
        if (packet == null) {
            return;
        }
        out.add(packet);
    }


}
