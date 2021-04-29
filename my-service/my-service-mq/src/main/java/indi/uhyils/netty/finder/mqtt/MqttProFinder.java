package indi.uhyils.netty.finder.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.exception.MqttControlPacketTypeNotFoundException;
import indi.uhyils.exception.MqttQosNotFoundException;
import indi.uhyils.netty.finder.Finder;
import indi.uhyils.netty.model.ProtocolParsingModel;
import indi.uhyils.netty.util.IpUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 数据位长度
     */
    private static final Integer DATA_BIT_LENGTH = 7;

    /**
     * 持续位代表后续还有长度位的标志
     */
    private static final Integer PERSISTENT_BIT_RIGHT = 1;

    /**
     * 长度位的最大长度
     */
    private static final Integer LENGTH_LENGTH_MAX = 4;
    /**
     * 一个小时
     */
    private static final Long ONE_HOUR = 60 * 60 * 1000L;
    /**
     * 重传记录,规定为一小时,如果超过一小时就删除
     */
    private static Map<Integer, Long> retranRecord = new HashMap<>();

    @Override
    public Boolean checkByteBuf(ByteBuf byteBuf) {
        int readerIndex = byteBuf.readerIndex();
        // 将第一位空出来
        byteBuf.readByte();
        //将读位置于length位后
        readLengthAndReadByteToLengthEnd(byteBuf);

        try {
            // MQTT固定报头加协议名称
            int mqttHeadLength = 6;
            byte[] mqttByte = new byte[mqttHeadLength];
            //获取协议名称
            for (int i = 0; i < mqttHeadLength; i++) {
                mqttByte[i] = byteBuf.readByte();
            }
            byte[] bytes = Arrays.copyOfRange(mqttByte, 4, 8);
            return MQTT_NAME.equals(new String(bytes));
        } finally {
            byteBuf.readerIndex(readerIndex);
        }
    }

    @Override
    public ByteBuf cutByteBuf(ByteBuf byteBuf) {
        int readerIndex = byteBuf.readerIndex();
        // 读出第一个byte
        byteBuf.readByte();
        int length = readLengthAndReadByteToLengthEnd(byteBuf);

        byteBuf.readerIndex(readerIndex);
        // 可变报头和负载获取
        int initialCapacity = length + 1;
        ByteBuf buffer = Unpooled.buffer(initialCapacity);
        byteBuf.readBytes(buffer);
        // 将截取出来的buf写指针置位最后一位
        buffer.writerIndex(readerIndex + initialCapacity);
        return buffer;
    }

    /**
     * 获取长度,并且将byteBuf读位至于长度之后
     *
     * @param byteBuf
     * @return
     */
    private int readLengthAndReadByteToLengthEnd(ByteBuf byteBuf) {
        // 获取 可变报头和负载部分的长度
        byte lengthHead = byteBuf.readByte();
        // 长度位的长度
        int lengthLength = 1;
        int length = lengthHead & 0b1111111;
        // length 是根据一个持续位和7个数据位组成,获取持续位判断是否还有多的
        while (((lengthHead >> DATA_BIT_LENGTH) & 0b1) == PERSISTENT_BIT_RIGHT && lengthLength < LENGTH_LENGTH_MAX) {
            lengthHead = byteBuf.readByte();
            // 拼接数据位
            length |= ((lengthHead & 0b1111111) << (lengthLength * DATA_BIT_LENGTH));
            lengthLength++;
        }
        return length;
    }

    @Override
    public ProtocolParsingModel parsingByteBuf(ChannelHandlerContext ctx, ByteBuf byteBuf) throws MqttControlPacketTypeNotFoundException, ClassNotFoundException, MqttQosNotFoundException {
        int allLength = byteBuf.readableBytes();
        byte firstByte = byteBuf.readByte();
        int length = readLengthAndReadByteToLengthEnd(byteBuf);
        //flag位
        int flag = firstByte & 0b1111;
        //信息标志位
        int mqttControlPacketType = (firstByte >> 4) & 0b111;
        MqttControlPacketTypeEnum parsing = MqttControlPacketTypeEnum.parsing(mqttControlPacketType);
        // 第一个报文标识符
        byte msb = byteBuf.readByte();
        // 第二个报文标识符
        byte lsb = byteBuf.readByte();
        byte[] body = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(body);
        // 解析body
        String bodyJson = new String(body);
        JSONArray objects = JSON.parseArray(bodyJson);
        Class[] paramsTypes = parsing.getParamsTypes();
        Object[] params = new Object[paramsTypes.length];
        for (int i = 0; i < paramsTypes.length; i++) {
            params[i] = objects.getObject(i, paramsTypes[i]);
        }
        if (parsing.isServiceMethod()) {
            // 只有publish特殊
            if (parsing == MqttControlPacketTypeEnum.PUBLISH) {
                // PUBLISH报文的保留标志
                int retain = flag & 0b1;
                // PUBLISH报文的服务质量等级
                int qos = (flag & 0b10) >> 1;
                // 控制报文的重复分发标志
                int dup = (flag & 0b100) >> 2;

                MqttQosEnum qosEnum = MqttQosEnum.parsing(qos);
                // 过滤重复
                if (qosEnum == MqttQosEnum.AT_LEAST_ONCE) {
                    if (retranRecord.containsKey(dup)) {
                        Long date = retranRecord.get(dup);
                        // 如果超过1小时.则重新获取
                        if (System.currentTimeMillis() - date > ONE_HOUR) {
                            Integer dupInteger = dup;
                            retranRecord.remove(dupInteger);
                        } else {
                            return null;
                        }
                    } else {
                        retranRecord.put(dup, System.currentTimeMillis());
                    }
                }
            }
            return ProtocolParsingModel.buildServiceModel(MQTT_NAME, IpUtil.getAddressStr(ctx), parsing.isKeepAlive(), parsing.getMethodName(), paramsTypes, params, this::packingByteToRightResponse);
        } else {
            return ProtocolParsingModel.buildReturnModel(MQTT_NAME, IpUtil.getAddressStr(ctx), parsing.isKeepAlive(), () -> parsing.getFunction().apply(body));
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
