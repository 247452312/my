package indi.uhyils.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


/**
 * mq 各种支持的协议的解码器
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月23日 09时19分
 */
public class MqByteToMessageDecoder extends ByteToMessageDecoder {

    /**
     * 尝试解析协议时,拆分协议的长度
     */
    private static final Integer TRY_FIND_BYTE_BUF_LENGTH = 100;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //注 因为内置了 字节缓冲,所以此处可以确认byteBuf的开头一定是某一个协议的开始
        ByteBuf buffer = Unpooled.buffer();
        in.readBytes(buffer, 0, TRY_FIND_BYTE_BUF_LENGTH);
        
    }

}
