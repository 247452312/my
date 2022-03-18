package indi.uhyils;

import indi.uhyils.decoder.Proto;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.apache.commons.io.HexDump;

/**
 * mysql协议解析方法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月18日 08时51分
 */
public final class MysqlUtil {


    /**
     * 解析mysql协议
     *
     * @param mysqlProtocol mysql协议二进制
     *
     * @return
     */
    public static String analysisMysqlProtocolToSql(byte[] mysqlProtocol) {

    }


    /**
     * 解析mysql语句
     *
     * @param sql mysql语句
     *
     * @return
     */
    public static List<MysqlPlan> analysisSqlToPlan(String sql) {

    }


    /**
     * 将协议解析为差不多看得懂的东西,但不能用 只能输出
     *
     * @param packet
     *
     * @return
     */
    public static String dump(byte[] packet) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            HexDump.dump(packet, 0, out, 0);
            return out.toString();
        } catch (IOException e) {
            LogUtil.error(e);
            return "";
        }
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
        size = getBytesSize(packet);

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

    public static int getBytesSize(byte[] packet) {
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
        Asserts.assertTrue(value >= 0, "long数值不能小于零");
        if (value <= 250) {
            return new byte[]{(byte) value};
        }
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


    public static int getBytesSize(long value) {
        boolean complex = false;
        if (value < 0) {
            value = -value;
            complex = true;
        }
        int count = 0;
        while (value > 0) {
            value >>= 8;
            count++;
        }
        if (complex) {
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
        int size = getBytesSize(value);
        byte[] result = new byte[size];
        for (int i = size - 1; i >= 0; i--) {
            result[i] = (byte) (value & 0x0FFL);
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
    public static byte[] toBytes(long value, int length) {
        byte[] bytes = toBytes(value);
        int size = bytes.length;
        byte[] result = new byte[length];
        if (length > size) {
            System.arraycopy(bytes, 0, result, length - size, size);
        } else {
            System.arraycopy(bytes, size - length, result, 0, length);
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

    /**
     * 加密密码
     *
     * @param pass 密码
     * @param seed 随机数
     *
     * @return
     *
     * @throws NoSuchAlgorithmException
     */
    public static final byte[] encodePassword(byte[] pass, byte[] seed) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            LogUtil.error(e);
        }
        byte[] pass1 = md.digest(pass);
        md.reset();
        byte[] pass2 = md.digest(pass1);
        md.reset();
        md.update(seed);
        byte[] pass3 = md.digest(pass2);
        for (int i = 0; i < pass3.length; i++) {
            pass3[i] = (byte) (pass3[i] ^ pass1[i]);
        }
        return pass3;
    }

    /**
     * 是否有权限
     *
     * @param ability       权限集
     * @param targetAbility 指定权限
     *
     * @return
     */
    public static final boolean hasAbility(long ability, long targetAbility) {
        return ((ability & targetAbility) == targetAbility);
    }

    /**
     * 获取两个数组是否一致
     *
     * @param firstBytes
     * @param secondBytes
     *
     * @return
     */
    public static boolean equals(byte[] firstBytes, byte[] secondBytes) {
        if (firstBytes == null || secondBytes == null) {
            return false;
        }

        if (firstBytes.length != secondBytes.length) {
            return false;
        }
        for (int i = 0; i < firstBytes.length; i++) {
            byte firstByte = firstBytes[i];
            byte secondByte = secondBytes[i];
            if (firstByte != secondByte) {
                return false;
            }
        }
        return true;
    }

}
