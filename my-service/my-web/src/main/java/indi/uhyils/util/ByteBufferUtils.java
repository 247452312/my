package indi.uhyils.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月09日 07时32分
 */
public class ByteBufferUtils {

    public static String decodeKey(ByteBuffer bytes) {
        Charset charset = StandardCharsets.UTF_8;
        return charset.decode(bytes).toString();
    }

    public static byte[] decodeValue(ByteBuffer bytes) {
        int len = bytes.limit() - bytes.position();
        byte[] bytes1 = new byte[len];
        bytes.get(bytes1);
        return bytes1;
    }

    public static ByteBuffer encodeKey(String key) {
        return ByteBuffer.wrap(key.getBytes(StandardCharsets.UTF_8));
    }

    public static ByteBuffer encodeValue(byte[] value) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(value.length);
        byteBuffer.clear();
        byteBuffer.get(value, 0, value.length);
        return byteBuffer;
    }
}
