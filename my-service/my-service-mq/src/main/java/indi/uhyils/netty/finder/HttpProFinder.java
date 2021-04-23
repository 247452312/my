package indi.uhyils.netty.finder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.charset.StandardCharsets;


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
     * http请求的开头
     */
    private static final String[] HTTP_TYPES = new String[]{"GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS", "CONNECT"};

    public static void main(String[] args) {

        String s = "POST /test HTTP/1.1\r\nAuthorization: Bearer 9cf2c3cb-3d44-4618-9805-e62da8eb18f9\r\nContent-Type: application/json\r\nUser-Agent: PostmanRuntime/7.26.8\r\nAccept: */*\r\nCache-Control: no-cache\r\nPostman-Token: d1f6ef45-764b-4ce0-8a0e-38ae0849d085\r\nHost: localhost:8080\r\nAccept-Encoding: gzip, deflate, br\r\nConnection: keep-alive\r\nContent-Length: 23\r\n\r\n{\"name\"=\"123\",\"age\"=22}";

        Finder httpProFinder = new HttpProFinder();
        ByteBuf byteBuf = Unpooled.buffer();

        byteBuf.writeBytes(s.getBytes(StandardCharsets.UTF_8));

        String asdasdasdasd = "asdasdasdasd";
        byteBuf.writeBytes(asdasdasdasd.getBytes(StandardCharsets.UTF_8));
        ByteBuf buffer = Unpooled.buffer(100);
        byteBuf.getBytes(0, buffer, 0, 100);
        buffer.writerIndex(100);
        Boolean aBoolean = httpProFinder.checkByteBuf(buffer);
        if (aBoolean) {
            ByteBuf httpByteBuf = httpProFinder.cutByteBuf(byteBuf);
            System.out.println(httpByteBuf.toString());
        }

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
        //将读指针置为 第一个\r\n之后
        firstCr = firstCr + 2;
        byteBuf.readerIndex(firstCr);
        int secondCr = -1;
        while (byteBuf.isReadable()) {
            if (byteBuf.readByte() == (byte) '\r'
                    && byteBuf.isReadable()
                    && byteBuf.readByte() == (byte) '\n'
                    && byteBuf.isReadable()
                    && byteBuf.readByte() == (byte) '\r'
                    && byteBuf.isReadable()
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
        //找到Content-Length
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
}
