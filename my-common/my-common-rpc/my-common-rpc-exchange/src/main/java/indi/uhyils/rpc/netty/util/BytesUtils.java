package indi.uhyils.rpc.netty.util;

import indi.uhyils.rpc.netty.content.MyRpcContent;
import io.netty.buffer.ByteBuf;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 09时54分
 */
public class BytesUtils {
    public static byte[] concat(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static byte[] getRpcHeadByByteBuf(ByteBuf in, Integer minLength) {
        byte[] startMsg = new byte[2];
        startMsg[0] = in.readByte();
        while (in.isReadable()) {
            // 标记字节流开始位置
            in.markReaderIndex();
            startMsg[1] = in.readByte();

            if (Objects.equals(MyRpcContent.AGREEMENT_START[0], startMsg[0]) && Objects.equals(MyRpcContent.AGREEMENT_START[1], startMsg[1])) {
                break;
            } else {
                startMsg[0] = startMsg[1];
            }
            // 重置读索引为0
            in.resetReaderIndex();
            // 长度校验，字节流长度至少x字节，小于x字节则等待下一次字节流过来
            if (in.readableBytes() < minLength) {
                in.resetReaderIndex();
                return null;
            }
        }
        return startMsg;
    }
}
