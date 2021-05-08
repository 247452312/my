package indi.uhyils.netty.handler;

import indi.uhyils.netty.exception.ByteToMessageException;
import indi.uhyils.netty.finder.Finder;
import indi.uhyils.netty.finder.http.HttpProFinder;
import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;

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
            //筛选是否有合适的解析器
            for (Finder entry : finders) {
                if (entry.checkByteBuf(buffer)) {
                    finder = entry;
                    break;
                }
            }
            // 如果没有合适的解析器,则跳出
            if (finder == null) {
                break;
            }
            ReferenceCountUtil.release(buffer);
            int readerIndex = in.readerIndex();
            // 将此次协议剪切下来
            ByteBuf byteBuf = finder.cutByteBuf(in);
            // 如果剪切失败,则重置读指针,并且跳出方法,这样会将之后的信息重新拼接到下一个buf中去
            if (byteBuf == null) {
                in.readerIndex(readerIndex);
                return;
            }
            // 将读指针指向下一个协议的开头
            in.readerIndex(readerIndex + byteBuf.readableBytes());
            if (LogUtil.isDebugEnabled(this)) {
                byte[] dst = new byte[byteBuf.readableBytes()];
                byteBuf.getBytes(0, dst);
                LogUtil.debug(this, new String(dst));
            }
            // 解析剪切下来的协议
            ProtocolParsingModel protocolParsingModel = finder.parsingByteBuf(ctx, byteBuf);
            ReferenceCountUtil.release(byteBuf);
            if (protocolParsingModel != null) {
                out.add(protocolParsingModel);
            }
        }

        //半包处理
        int canReadLength = in.readableBytes();
        // 这里是不可能的,只要走到这里就说明有陌生的东西进来了
        if (canReadLength > TRY_FIND_BYTE_BUF_LENGTH) {
            // 把陌生的东西删掉
            byte[] dst = new byte[canReadLength];
            in.readBytes(dst);
            LogUtil.error(new ByteToMessageException("错误,有一个信息MQ不能解析,请人工介入: 错误信息:" + new String(dst)));
        }
    }
}
