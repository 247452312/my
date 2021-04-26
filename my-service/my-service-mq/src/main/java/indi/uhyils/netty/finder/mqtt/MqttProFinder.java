package indi.uhyils.netty.finder.mqtt;

import indi.uhyils.netty.finder.Finder;
import indi.uhyils.netty.model.ProtocolParsingModel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * MQTT 协议解析
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月26日 08时59分
 */
public class MqttProFinder implements Finder {

    /**
     * 协议名称
     */
    private static final String MQTT_NAME = "MQTT";

    @Override
    public Boolean checkByteBuf(ByteBuf byteBuf) {
        int readerIndex = byteBuf.readerIndex();
        try {
            // MQTT固定报头加协议名称
            int mqttHeadLength = 8;
            byte[] mqttByte = new byte[mqttHeadLength];
            //获取协议名称
            for (int i = 0; i < mqttHeadLength; i++) {
                mqttByte[i] = byteBuf.readByte();
            }
            byte[] bytes = Arrays.copyOfRange(mqttByte, 4, 8);
            if (MQTT_NAME.equals(new String(bytes))) {
                return true;
            }
            return false;
        } finally {
            byteBuf.readerIndex(readerIndex);
        }
    }

    @Override
    public ByteBuf cutByteBuf(ByteBuf byteBuf) {
        byte first = byteBuf.readByte();
        // 可变报头和负载部分的长度
        byte remainingLength = byteBuf.readByte();
        // 可变报头和负载获取 掩码.保证不受最高位影响
        byte[] dst = new byte[remainingLength & 0b1111111];
        byteBuf.readBytes(dst);
        String s = new String(dst);
        System.out.println(s);

        return null;
    }

    @Override
    public ProtocolParsingModel parsingByteBuf(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        return null;
    }

    @Override
    public Boolean packingByteToRightResponse(ChannelHandlerContext ctx, Object returnObj) {
        return null;
    }

    @Override
    public void addPrepositionHandler(ChannelHandlerContext ctx, ByteBuf byteBuf) {

    }
}
