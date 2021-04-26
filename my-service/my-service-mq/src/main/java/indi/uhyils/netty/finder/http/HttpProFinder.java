package indi.uhyils.netty.finder.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.netty.finder.Finder;
import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.math.NumberUtils;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

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
            new String[]{"GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS", "CONNECT"};

    /**
     * 获取请求者的ip
     *
     * @param ctx
     * @return
     */
    public static String getAddressStr(ChannelHandlerContext ctx) {
        InetSocketAddress socket = (InetSocketAddress) ctx.channel().remoteAddress();
        return socket.getAddress().getHostAddress();
    }

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
            if (byteBuf.readByte() == (byte) '\r' && byteBuf.isReadable() && byteBuf.readByte() == (byte) '\n'
                    && byteBuf.isReadable() && byteBuf.readByte() == (byte) '\r' && byteBuf.isReadable()
                    && byteBuf.readByte() == (byte) '\n') {
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
        String http = new String(byteBuf.array(), StandardCharsets.UTF_8);
        // 获取请求人的id
        String ip = getAddressStr(ctx);

        int firstSeparator = http.indexOf(SEPARATOR);
        String twoSeparator = SEPARATOR + SEPARATOR;
        int headerAndBodySeparator = http.indexOf(twoSeparator);
        // 获取header
        String headerStr = http.substring(firstSeparator, headerAndBodySeparator);
        // 获取body
        String bodyStr = http.substring(headerAndBodySeparator + twoSeparator.length());
        JSONObject body = JSONObject.parseObject(bodyStr);

        String methodName = body.getString(METHOD_NAME);
        JSONArray methodType = body.getJSONArray(PARAMS_TYPE);
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
        if (data[0] instanceof DefaultRequest) {
            actionAddRequestLink((DefaultRequest) data[0]);
        }

        return ProtocolParsingModel.build(HTTP, ip, methodName, methodClassType, data,
                this::packingByteToRightResponse);
    }

    /**
     * action 添加链路跟踪起点
     *
     * @param action
     */
    public static void actionAddRequestLink(DefaultRequest action) {
        LinkNode<String> link = new LinkNode<>();
        link.setData("MQ请求");
        action.setRequestLink(link);
    }

    @Override
    public Boolean packingByteToRightResponse(ChannelHandlerContext ctx, Object returnObj) {

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8");
        String string = JSONObject.toJSONString(returnObj);
        ByteBuf buffer = Unpooled.copiedBuffer(string, CharsetUtil.UTF_8);
        // 设置返回内容的长度
        int value = buffer.readableBytes();
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, value);
        response.content().writeBytes(buffer);
        buffer.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        return true;
    }

    @Override
    public void addPrepositionHandler(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        ctx.pipeline().addFirst("httpResponseEncoder", new HttpResponseEncoder());
    }


}
