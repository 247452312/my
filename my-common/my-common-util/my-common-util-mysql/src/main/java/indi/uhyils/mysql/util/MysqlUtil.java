package indi.uhyils.mysql.util;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.mysql.decode.Proto;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.parser.SqlParser;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import indi.uhyils.util.StringUtil;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.HexDump;

/**
 * mysql协议解析方法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月18日 08时51分
 */
public final class MysqlUtil {

    /**
     * 单引号前缀
     */
    private static final String QUOTES_PREFIX = "`";

    /**
     * 解析mysql语句
     *
     * @param sql mysql语句
     *
     * @return
     */
    public static List<MysqlPlan> analysisSqlToPlan(String sql) {
        return analysisSqlToPlan(sql, new HashMap<>());
    }

    /**
     * 解析mysql语句
     *
     * @param sql mysql语句
     *
     * @return
     */
    public static List<MysqlPlan> analysisSqlToPlan(String sql, Map<String, String> headers) {
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        List<SqlParser> beans = SpringUtil.getBeans(SqlParser.class);
        for (SqlParser bean : beans) {
            if (bean.canParse(sqlStatement)) {
                return bean.parse(sqlStatement, headers);
            }
        }
        Asserts.throwException("解析执行计划失败:{}", sql);
        return null;
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

    public static byte[] mergeLengthCodedBinary(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        return byteToLengthCodeBinary(bytes);

    }

    public static byte[] fixString(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[bytes.length + 1];
        result[bytes.length] = 0x00;
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        return result;
    }

    public static byte[] varString(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[bytes.length + 1];
        result[0] = (byte) bytes.length;
        System.arraycopy(bytes, 0, result, 1, bytes.length);
        return result;
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

    /**
     * 匹配like
     *
     * @param key          字段名称
     * @param variableName 入参(可能带有%)
     *
     * @return
     */
    @NotNull
    public static boolean likeMatching(String key, String variableName) {
        // todo 匹配like
        return false;
    }


    /**
     * sha1加密
     *
     * @param password
     *
     * @return
     */
    public static String sha1(String password) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 忽略单引号以及大小写匹配表名
     *
     * @param table
     * @param targetTable
     *
     * @return
     */
    public static boolean equalsIgnoreCaseAndQuotes(String table, String targetTable) {
        String rqTable = removeQuotes(table);
        String rqTargetTable = removeQuotes(targetTable);
        return StringUtil.equalsIgnoreCase(rqTable, rqTargetTable);
    }

    /**
     * 去除表名中的单引号
     *
     * @param table
     *
     * @return
     */
    private static String removeQuotes(String table) {
        if (table.startsWith(QUOTES_PREFIX) && table.endsWith(QUOTES_PREFIX)) {
            return table.substring(1, table.length() - 1);
        }
        return table;
    }

    /**
     * 忽略大小写以及单引号
     *
     * @param needField
     * @param key
     *
     * @return
     */
    public static boolean ignoreCaseAndQuotesContains(List<String> needField, String key) {
        final String rKey = removeQuotes(key);
        return needField.stream().anyMatch(t -> StringUtil.equalsIgnoreCase(removeQuotes(t), rKey));
    }
}
