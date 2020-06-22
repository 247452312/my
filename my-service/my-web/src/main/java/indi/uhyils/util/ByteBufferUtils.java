package indi.uhyils.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月09日 07时32分
 */
public class ByteBufferUtils {

    public static String decodeKey(ByteBuffer bytes) {
        Charset charset = Charset.forName("utf-8");
        return charset.decode(bytes).toString();
    }

    public static byte[] decodeValue(ByteBuffer bytes) {
        int len = bytes.limit() - bytes.position();
        byte[] bytes1 = new byte[len];
        bytes.get(bytes1);
        return bytes1;
    }

    public static ByteBuffer encodeKey(String key) {
        try {
            return ByteBuffer.wrap(key.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            LogUtil.error(ByteBufferUtils.class,e);
        }
        return ByteBuffer.wrap(key.getBytes());
    }

    public static ByteBuffer encodeValue(byte[] value) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(value.length);
        byteBuffer.clear();
        byteBuffer.get(value, 0, value.length);
        return byteBuffer;
    }
}
