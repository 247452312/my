package indi.uhyils.netty.util;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

import static io.netty.handler.codec.http.HttpConstants.*;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月30日 13时38分
 */
public class HttpResponseUtil {

    /**
     * 空格
     */
    private static final byte SP = 32;


    /**
     * crlf
     */
    private static final int CRLF_SHORT = (CR << 8) | LF;

    /**
     * :+空格
     */
    private static final int COLON_AND_SPACE_SHORT = (COLON << 8) | SP;

    /**
     * http返回值请求转byte
     *
     * @return
     */
    public static void msgToByte(ByteBuf buf, FullHttpResponse response) {
        // HTTP/1.1
        buf.writeBytes(response.protocolVersion().toString().getBytes(StandardCharsets.UTF_8));
        // 空格
        buf.writeByte(SP);
        // 200 OK
        buf.writeBytes(response.status().toString().getBytes(StandardCharsets.UTF_8));
        // /r/n
        ByteBufUtil.writeShortBE(buf, CRLF_SHORT);
        writeHeads(buf, response);
        // /r/n
        ByteBufUtil.writeShortBE(buf, CRLF_SHORT);
        ByteBuf content = response.content();
        buf.writeBytes(content);
    }


    /**
     * 获取一个成功的response
     *
     * @param returnObj
     * @return
     */
    public static FullHttpResponse getOkResponse(Object returnObj) {
        return getResponse(returnObj, HttpResponseStatus.OK);
    }

    /**
     * 获取一个response
     *
     * @param returnObj
     * @return
     */
    public static FullHttpResponse getResponse(Object returnObj, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8");
        String string = JSONObject.toJSONString(returnObj);
        ByteBuf buffer = Unpooled.copiedBuffer(string, CharsetUtil.UTF_8);
        // 设置返回内容的长度
        int value = buffer.readableBytes();
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, value);
        response.content().writeBytes(buffer);
        return response;
    }

    /**
     * 填充header
     *
     * @param buf
     * @param response
     */
    private static void writeHeads(ByteBuf buf, HttpResponse response) {
        Iterator<Map.Entry<CharSequence, CharSequence>> iter = response.headers().iteratorCharSequence();
        while (iter.hasNext()) {
            Map.Entry<CharSequence, CharSequence> header = iter.next();
            CharSequence name = header.getKey();
            CharSequence value = header.getValue();
            final int nameLen = name.length();
            final int valueLen = value.length();
            final int entryLen = nameLen + valueLen + 4;
            buf.ensureWritable(entryLen);
            int offset = buf.writerIndex();
            writeAscii(buf, offset, name);
            offset += nameLen;
            ByteBufUtil.setShortBE(buf, offset, COLON_AND_SPACE_SHORT);
            offset += 2;
            writeAscii(buf, offset, value);
            offset += valueLen;
            ByteBufUtil.setShortBE(buf, offset, CRLF_SHORT);
            offset += 2;
            buf.writerIndex(offset);
        }
    }

    private static void writeAscii(ByteBuf buf, int offset, CharSequence value) {
        if (value instanceof AsciiString) {
            ByteBufUtil.copy((AsciiString) value, 0, buf, offset, value.length());
        } else {
            buf.setCharSequence(offset, value, CharsetUtil.US_ASCII);
        }
    }


    public static void main(String[] args) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8");
        String string = "aaaa";
        ByteBuf buffer = Unpooled.copiedBuffer(string, CharsetUtil.UTF_8);
        // 设置返回内容的长度
        int value = buffer.readableBytes();
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, value);
        response.content().writeBytes(buffer);
        HttpResponseStatus status = response.status();
        System.out.println(status.toString());
    }

}
