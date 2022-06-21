package indi.uhyils.netty.util;

import com.alibaba.fastjson.JSON;
import indi.uhyils.core.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.charset.StandardCharsets;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年05月08日 08时01分
 */
public class NettyMessageUtil {

    /**
     * 消息转byteBuf
     *
     * @param message
     *
     * @return
     */
    public static ByteBuf msgToByte(Message message) {
        String s = JSON.toJSONString(message);
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        ByteBuf buffer = Unpooled.buffer(bytes.length);
        buffer.writeBytes(buffer);
        return buffer;
    }
}
