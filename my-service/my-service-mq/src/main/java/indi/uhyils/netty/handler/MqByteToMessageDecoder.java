package indi.uhyils.netty.handler;

import indi.uhyils.netty.finder.Finder;
import indi.uhyils.netty.finder.http.HttpProFinder;
import indi.uhyils.netty.finder.mqtt.MqttProFinder;
import indi.uhyils.netty.model.ProtocolParsingModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.ArrayList;
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
    /**
     * 协议解析器
     */
    private List<Finder> finders;

    public MqByteToMessageDecoder() {
        // TODO 此处可以重构为spi机制
        finders = new ArrayList<>();
        finders.add(new HttpProFinder());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 注 因为内置了 字节缓冲,所以此处可以确认byteBuf的开头一定是某一个协议的开始
        while (in.isReadable()) {
            ByteBuf buffer = Unpooled.buffer();
            //先拆出100位来进行判断是哪一个协议
            in.getBytes(in.readerIndex(), buffer, 0, TRY_FIND_BYTE_BUF_LENGTH);
            buffer.writerIndex(TRY_FIND_BYTE_BUF_LENGTH);
            Finder finder = null;
            for (Finder entry : finders) {
                if (entry.checkByteBuf(buffer)) {
                    finder = entry;
                    break;
                }
            }
            if (finder == null) {
                break;
            }
            buffer.release();
            //添加前置需要的handler
            finder.addPrepositionHandler(ctx, in);
            ByteBuf byteBuf = finder.cutByteBuf(in);
            ProtocolParsingModel protocolParsingModel = finder.parsingByteBuf(ctx, byteBuf);
            byteBuf.release();
            if (protocolParsingModel != null) {
                out.add(protocolParsingModel);
            }
        }

    }
}
