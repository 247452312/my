package indi.uhyils.netty.finder.mqtt;

import indi.uhyils.exception.MqttControlPacketTypeNotFoundException;
import indi.uhyils.netty.finder.Finder;
import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.netty.util.IpUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

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
        int readerIndex = byteBuf.readerIndex();
        // 读出第一个byte
        byteBuf.readByte();
        // 获取 可变报头和负载部分的长度
        byte remainingLength = byteBuf.readByte();
        byteBuf.readerIndex(readerIndex);
        // 可变报头和负载获取 掩码.保证不受最高位影响
        int initialCapacity = remainingLength & 0b1111111 + 2;
        ByteBuf buffer = Unpooled.buffer(initialCapacity);
        byteBuf.readBytes(buffer);
        // 将截取出来的buf写指针置位最后一位
        buffer.writerIndex(initialCapacity);
        return buffer;
    }

    @Override
    public ProtocolParsingModel parsingByteBuf(ChannelHandlerContext ctx, ByteBuf byteBuf) throws MqttControlPacketTypeNotFoundException {
        int allLength = byteBuf.readableBytes();
        byte firstByte = byteBuf.readByte();
        int length = byteBuf.readByte() & 0b1111111;
        int flag = firstByte & 0b1111;
        int mqttControlPacketType = (firstByte >> 4) & 0b111;
        MqttControlPacketTypeEnum parsing = MqttControlPacketTypeEnum.parsing(mqttControlPacketType);
        if (parsing.isServiceMethod()) {
            // 只有publish特殊
            if (parsing == MqttControlPacketTypeEnum.PUBLISH) {

            } else {

            }
            return null;
        } else {
            byte[] dst = new byte[allLength];
            byteBuf.getBytes(0, dst);
            return ProtocolParsingModel.buildReturnModel(MQTT_NAME, IpUtil.getAddressStr(ctx), parsing.isKeepAlive(), () -> parsing.getFunction().apply(dst));
        }
    }

    @Override
    public Boolean packingByteToRightResponse(ChannelHandlerContext ctx, Object returnObj) {
        return null;
    }

    @Override
    public void addPrepositionHandler(ChannelHandlerContext ctx, ByteBuf byteBuf) {

    }
}
