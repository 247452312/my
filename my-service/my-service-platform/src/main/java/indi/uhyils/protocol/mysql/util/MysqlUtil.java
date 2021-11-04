package indi.uhyils.protocol.mysql.util;

import indi.uhyils.protocol.mysql.decoder.impl.Proto;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
     * 合并多个属性为字节数组
     *
     * @return
     */
    public static byte[] mergeObjsToByte(long rowSize, long indexId, int serverStatus, int warnCount, String msg) {
        List<byte[]> listResult = new ArrayList<>();
        int count = 0;
        // 添加影响行数报文
        byte[] e = mergeLengthCodedBinary(rowSize);
        listResult.add(e);
        count += e.length;
        // 添加索引id值
        byte[] e1 = mergeLengthCodedBinary(indexId);
        listResult.add(e1);
        count += e1.length;
        // 添加服务器状态
        byte[] e2 = toBytes(serverStatus);
        listResult.add(e2);
        count += e2.length;
        // 添加告警计数
        byte[] e3 = toBytes(warnCount);
        listResult.add(e3);
        count += e3.length;
        // 添加服务器消息
        byte[] bytes1 = msg.getBytes(StandardCharsets.UTF_8);
        listResult.add(bytes1);
        count += bytes1.length;

        byte[] result = new byte[count];
        int index = 0;
        for (byte[] bytes : listResult) {
            System.arraycopy(bytes, 0, result, index, bytes.length);
            index += bytes.length;
        }
        return result;

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
        int size = bytes.length;

        byte[] lengthByte = new byte[0];
        if (size == 0) {
            lengthByte = new byte[]{(byte) 251};
        } else if (value < 250) {
            lengthByte = new byte[]{(byte) size};
        } else if (size <= 2) {
            lengthByte = new byte[3];
            lengthByte[0] = (byte) 252;
            System.arraycopy(bytes, 0, lengthByte, 1, 2);
        } else if (size <= 3) {
            lengthByte = new byte[4];
            lengthByte[0] = (byte) 253;
            System.arraycopy(bytes, 0, lengthByte, 1, 3);
        } else if (size <= 8) {
            lengthByte = new byte[9];
            lengthByte[0] = (byte) 254;
            int offset = 8 - bytes.length;
            System.arraycopy(bytes, 0, lengthByte, 1 + offset, bytes.length);
        }
        return lengthByte;
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

}
