package indi.uhyils.netty.finder;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.math.NumberUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;

/**
 * http协议发现者
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月23日 09时23分
 */
public class HttpProFinder implements Finder {
    /**
     * http请求名称
     */
    private static final String HTTP_NAME = "HTTP/1.1";
    private static final String HTTP = "HTTP";
    /**
     * http分隔符
     */
    private static final String SEPARATOR = "\r\n";

    /**
     * http请求的开头
     */
    private static final String[] HTTP_TYPES =
        new String[] {"GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS", "CONNECT"};

    @Override
    public Boolean checkByteBuf(ByteBuf byteBuf) {

        boolean typeNameIsHttp = checkTypeNameIsHttp(byteBuf);
        if (!typeNameIsHttp) {
            return false;
        }
        int i = byteBuf.forEachByte(ByteProcessor.FIND_CRLF);
        return checkHttpName(byteBuf, i);
    }

    /**
     * 检查是否有http字样
     *
     * @param byteBuf
     * @param crlfIndex
     * @return
     */
    private Boolean checkHttpName(ByteBuf byteBuf, int crlfIndex) {
        int length = HTTP_NAME.length();
        if (crlfIndex < length) {
            return false;
        }
        byte[] httpDemo = new byte[HTTP.length()];
        byteBuf.getBytes(crlfIndex - length, httpDemo);
        String httpName = new String(httpDemo, StandardCharsets.UTF_8);
        return httpName.equals(HTTP);
    }

    private boolean checkTypeNameIsHttp(ByteBuf byteBuf) {
        // httpTypes 的所有类型的最大长度
        int type = 7;
        byte[] bytes = new byte[type];
        byteBuf.getBytes(0, bytes);
        String typeName = new String(bytes, StandardCharsets.UTF_8);
        boolean typeNameIsHttp = false;
        for (String httpType : HTTP_TYPES) {
            if (typeName.contains(httpType)) {
                typeNameIsHttp = true;
                break;
            }
        }
        return typeNameIsHttp;
    }

    @Override
    public ByteBuf cutByteBuf(ByteBuf byteBuf) {
        // 初始readerIndex的记录
        int readerIndexMark = byteBuf.readerIndex();
        // 寻找第一个\r\n
        int firstCr = byteBuf.forEachByte(ByteProcessor.FIND_CRLF);
        if (firstCr == -1) {
            return null;
        }
        // 将读指针置为 第一个\r\n之后
        firstCr = firstCr + 2;
        byteBuf.readerIndex(firstCr);
        int secondCr = -1;
        while (byteBuf.isReadable()) {
            if (byteBuf.readByte() == (byte)'\r' && byteBuf.isReadable() && byteBuf.readByte() == (byte)'\n'
                && byteBuf.isReadable() && byteBuf.readByte() == (byte)'\r' && byteBuf.isReadable()
                && byteBuf.readByte() == (byte)'\n') {
                secondCr = byteBuf.readerIndex() - 4;
                break;
            }
        }
        if (secondCr == -1) {
            return null;
        }
        // 找到header
        int headLength = secondCr - firstCr;
        byte[] headerByte = new byte[headLength];
        byteBuf.getBytes(firstCr, headerByte, 0, headLength);
        String headerStr = new String(headerByte, StandardCharsets.UTF_8);
        String[] headers = headerStr.split("\r\n");
        // 找到Content-Length
        for (String header : headers) {
            if (header.startsWith("Content-Length")) {
                String[] split = header.split(": ");
                String length = split[1];
                if (!NumberUtils.isCreatable(length)) {
                    return null;
                }
                Integer httpBodyLength = NumberUtils.createInteger(length);
                int httpLength = firstCr + headLength + 4 + httpBodyLength;
                ByteBuf httpBuffer = Unpooled.buffer(httpLength);
                byteBuf.getBytes(readerIndexMark, httpBuffer, readerIndexMark, httpLength);
                byteBuf.readerIndex(readerIndexMark + httpLength);

                return httpBuffer;
            }
        }
        return null;
    }

    @Override
    public ProtocolParsingModel parsingByteBuf(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        String ip = getAddressStr(ctx);
        byte[] httpByte = byteBuf.array();
        String http = new String(httpByte, StandardCharsets.UTF_8);
        int firstSeparator = http.indexOf(SEPARATOR);
        int headerAndBodySeparator = http.indexOf(SEPARATOR + SEPARATOR);
        // 这个是header 可能之后能用到
        String headerString = http.substring(firstSeparator, headerAndBodySeparator);
        String substring = http.substring(headerAndBodySeparator);
        JSONObject body = JSONObject.parseObject(substring);
        String methodName = body.getString(METHOD_NAME);
        JSONArray methodType = body.getJSONArray(METHOD_TYPE);
        Class[] methodClassType = new Class[methodType.size()];
        try {
            for (int i = 0; i < methodType.size(); i++) {
                String className = methodType.getString(i);
                methodClassType[i] = Class.forName(className);
            }
        } catch (ClassNotFoundException e) {
            LogUtil.error(this, e);
        }
        JSONArray dataArray = body.getJSONArray(DATA);
        Object[] data = new Object[dataArray.size()];
        for (int i = 0; i < dataArray.size(); i++) {
            JSONObject o = dataArray.getJSONObject(i);
            Class aClass = methodClassType[i];
            data[i] = o.toJavaObject(aClass);

        }
        return ProtocolParsingModel.build(HTTP, ip, methodName, methodClassType, data,
            this::packingByteToRightResponse);
    }

    /**
     * 获取请求者的ip
     * 
     * @param ctx
     * @return
     */
    private String getAddressStr(ChannelHandlerContext ctx) {
        InetSocketAddress socket = (InetSocketAddress)ctx.channel().remoteAddress();
        return socket.getAddress().getHostAddress();
    }

    @Override
    public Object packingByteToRightResponse(Object returnObj) {
        // 1.设置响应
        return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
            Unpooled.copiedBuffer(JSONObject.toJSONString(returnObj), CharsetUtil.UTF_8));
    }
}
