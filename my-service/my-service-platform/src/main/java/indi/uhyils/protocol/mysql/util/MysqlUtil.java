package indi.uhyils.protocol.mysql.util;

import indi.uhyils.protocol.mysql.decoder.impl.Proto;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 09时50分
 */
public final class MysqlUtil {

    private MysqlUtil() {
    }

    public static byte[] readPacket(ByteBuf in) {
        int size;
        byte[] packet = new byte[3];
        if (in.readableBytes() < 3) {
            return null;
        }
        //如果出现半包问题，回溯
        in.markReaderIndex();
        int offset = 0;
        int target = 3;
        in.readBytes(packet, offset, (target - offset));
        size = getSize(packet);

        byte[] packetTmp = new byte[size + 4];
        System.arraycopy(packet, 0, packetTmp, 0, 3);
        packet = packetTmp;

        offset = offset + target;
        target = packet.length;
        if (in.readableBytes() < (target - offset)) {
            in.resetReaderIndex();
            return null;
        }
        in.readBytes(packet, offset, (target - offset));
        return packet;
    }

    public static int getSize(byte[] packet) {
        return (int) new Proto(packet).getFixedInt(3);
    }

    /**
     * long转Length Coded Binary
     *
     * @param value
     *
     * @return
     */
    public static byte[] mergeLengthCodedBinary(long value) {
        byte[] bytes = toBytes(value);
        return byteToLengthCodeBinary(bytes);
    }

    /**
     * long转Length Coded Binary
     *
     * @param value
     *
     * @return
     */
    public static byte[] mergeLengthCodedBinary(double value) {
        byte[] bytes = toBytes(value);
        return byteToLengthCodeBinary(bytes);
    }

    private static byte[] toBytes(double value) {
        long longValue = Double.doubleToRawLongBits(value);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((longValue >> 8 * i) & 0xff);
        }
        return byteRet;
    }

    /**
     * 字节数组转 Length Coded Binary
     *
     * @param bytes
     *
     * @return
     */
    public static byte[] byteToLengthCodeBinary(byte[] bytes) {
        // 数据真实长度
        int length = bytes.length;
        byte[] prefixByte;
        if (length == 0) {
            // 数据长度为0 前缀只有一个字节 且为251
            prefixByte = new byte[]{(byte) 251};
        } else if (length <= 250) {
            // 数据真实长度不到251 则第一个字节就是数据真实长度
            prefixByte = toBytes(length);
        } else {
            // 长度的字节
            byte[] lengthByte = toBytes(length);
            // 长度的字节的长度
            int lengthByteLength = lengthByte.length;
            // 长度字节的长度等于二
            if (lengthByteLength == 2) {
                prefixByte = new byte[3];
                prefixByte[0] = (byte) 252;
                System.arraycopy(lengthByte, 0, prefixByte, 1, lengthByteLength);
            } else if (lengthByteLength == 3) {
                prefixByte = new byte[4];
                prefixByte[0] = (byte) 253;
                System.arraycopy(lengthByte, 0, prefixByte, 1, lengthByteLength);
            } else {
                prefixByte = new byte[9];
                prefixByte[0] = (byte) 254;
                int offset = 8 - lengthByteLength;
                System.arraycopy(lengthByte, 0, prefixByte, 1 + offset, lengthByteLength);
            }
        }

        byte[] result = new byte[prefixByte.length + bytes.length];
        System.arraycopy(prefixByte, 0, result, 0, prefixByte.length);
        System.arraycopy(bytes, 0, result, prefixByte.length, bytes.length);
        return result;
    }

    public static byte[] mergeLengthCodedBinary(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        return byteToLengthCodeBinary(bytes);

    }

    /**
     * 字符串转 Null-Terminated String
     *
     * @param value
     *
     * @return
     */
    public static byte[] toNullTerminatedString(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        result[bytes.length] = 0x00;
        return result;
    }

    public static int getSize(long value) {
        int count = 0;
        while (value > 0) {
            value >>= 8;
            count++;
        }
        return count;
    }

    /**
     * long转byte数组
     *
     * @param value
     *
     * @return
     */
    public static byte[] toBytes(long value) {
        int size = getSize(value);
        byte[] result = new byte[size];
        for (int i = size - 1; i >= 0 && value > 0; i--) {
            result[i] = (byte) (value & 0xffL);
            value >>= 8;
        }
        return result;
    }

    /**
     * long转byte数组
     *
     * @param value
     *
     * @return
     */
    public static byte[] toBytes(int value) {
        int size = getSize(value);
        byte[] result = new byte[size];
        for (int i = size - 1; i >= 0 && value > 0; i--) {
            result[i] = (byte) (value & 0xffL);
            value >>= 4;
        }
        return result;
    }

    /**
     * long转byte数组
     *
     * @param value
     *
     * @return
     */
    public static byte[] toBytes(short value) {
        int size = getSize(value);
        byte[] result = new byte[size];
        for (int i = size - 1; i >= 0 && value > 0; i--) {
            result[i] = (byte) (value & 0xffL);
            value >>= 2;
        }
        return result;
    }

    /**
     * 合并bytesList
     *
     * @param listResult
     * @param count
     *
     * @return
     */
    public static byte[] mergeListBytes(List<byte[]> listResult, int count) {
        byte[] result = new byte[count];
        int index = 0;
        for (byte[] bytes : listResult) {
            System.arraycopy(bytes, 0, result, index, bytes.length);
            index += bytes.length;
        }
        return result;
    }

    /**
     * 合并bytesList
     *
     * @param listResult
     *
     * @return
     */
    public static byte[] mergeListBytes(List<byte[]> listResult) {
        int count = 0;
        for (byte[] bytes : listResult) {
            count += bytes.length;
        }
        return mergeListBytes(listResult, count);
    }

    public static byte[] buildFixedInt(int size, long value) {
        byte[] packet = new byte[size];

        if (size == 8) {
            packet[0] = (byte) ((value >> 0) & 0xFF);
            packet[1] = (byte) ((value >> 8) & 0xFF);
            packet[2] = (byte) ((value >> 16) & 0xFF);
            packet[3] = (byte) ((value >> 24) & 0xFF);
            packet[4] = (byte) ((value >> 32) & 0xFF);
            packet[5] = (byte) ((value >> 40) & 0xFF);
            packet[6] = (byte) ((value >> 48) & 0xFF);
            packet[7] = (byte) ((value >> 56) & 0xFF);
        } else if (size == 6) {
            packet[0] = (byte) ((value >> 0) & 0xFF);
            packet[1] = (byte) ((value >> 8) & 0xFF);
            packet[2] = (byte) ((value >> 16) & 0xFF);
            packet[3] = (byte) ((value >> 24) & 0xFF);
            packet[4] = (byte) ((value >> 32) & 0xFF);
            packet[5] = (byte) ((value >> 40) & 0xFF);
        } else if (size == 4) {
            packet[0] = (byte) ((value >> 0) & 0xFF);
            packet[1] = (byte) ((value >> 8) & 0xFF);
            packet[2] = (byte) ((value >> 16) & 0xFF);
            packet[3] = (byte) ((value >> 24) & 0xFF);
        } else if (size == 3) {
            packet[0] = (byte) ((value >> 0) & 0xFF);
            packet[1] = (byte) ((value >> 8) & 0xFF);
            packet[2] = (byte) ((value >> 16) & 0xFF);
        } else if (size == 2) {
            packet[0] = (byte) ((value) & 0xFF);
            packet[1] = (byte) ((value >> 8) & 0xFF);
        } else if (size == 1) {
            packet[0] = (byte) ((value) & 0xFF);
        } else {
            return null;
        }
        return packet;
    }

}
