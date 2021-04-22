package indi.uhyils.netty.handler;

import indi.uhyils.netty.MqNettyAbstractHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ByteProcessor;


/**
 * 接收http协议的nettyHandler
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月22日 09时57分
 */
public class HttpMqNettyAbstractHandler extends MqNettyAbstractHandler {

    /**
     * 本协议的名称
     */
    private static final String PROTOCOL_NAME = "HTTP";

    /**
     * http/1.1长度
     */
    private static final Integer HTTP_PROTOCOL_LENGTH = 8;

    @Override
    public String getProtocolName() {
        return PROTOCOL_NAME;
    }

    @Override
    protected NettyParsingPackage parsingNetty(ChannelHandlerContext ctx, ByteBuf msg) {

        /**
         * \n\r 的index
         */
        int crLfIndex = msg.forEachByte(ByteProcessor.FIND_CRLF);
        if (crLfIndex < HTTP_PROTOCOL_LENGTH) {
            return null;
        }
        byte[] httpProtocolByte = new byte[HTTP_PROTOCOL_LENGTH];
        msg.readerIndex(crLfIndex - HTTP_PROTOCOL_LENGTH);
        msg.readBytes(httpProtocolByte, 0, HTTP_PROTOCOL_LENGTH);
        msg.resetReaderIndex();
        String httpProtocol = new String(httpProtocolByte);
        if (!httpProtocol.toUpperCase().contains(getProtocolName())) {
        }
        return null;
    }
}
