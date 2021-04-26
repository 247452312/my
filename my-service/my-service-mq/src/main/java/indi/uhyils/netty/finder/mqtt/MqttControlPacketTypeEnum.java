package indi.uhyils.netty.finder.mqtt;

import indi.uhyils.exception.MqttControlPacketTypeNotFoundException;

import java.util.function.Function;

/**
 * 控制报文类型 MQTT的4-7位
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月26日 19时03分
 */
public enum MqttControlPacketTypeEnum {
    /**
     * 意义同msg
     */
    CONNECT((byte) 1, "客户端请求连接服务端",bytes -> {
        return bytes;
    }),
    CONNACK((byte) 2, "连接报文确认"),
    PUBLISH((byte) 3, "发布消息"),
    PUBACK((byte) 4, "QoS 1消息发布收到确认"),
    PUBREC((byte) 5, "发布收到（保证交付第一步）"),
    PUBREL((byte) 6, "发布释放（保证交付第二步）"),
    PUBCOMP((byte) 7, "QoS 2消息发布完成（保证交互第三步）"),
    SUBSCRIBE((byte) 8, "客户端订阅请求"),
    SUBACK((byte) 9, "订阅请求报文确认"),
    UNSUBSCRIBE((byte) 10, "客户端取消订阅请求"),
    UNSUBACK((byte) 11, "取消订阅报文确认"),
    PINGREQ((byte) 12, "心跳请求"),
    PINGRESP((byte) 13, "心跳响应"),
    DISCONNECT((byte) 14, "客户端断开连接");


    /**
     * 对应的代码
     */
    private byte code;
    /**
     * 描述
     */
    private String msg;
    /**
     * 是否是MQ本家的方法,如果不是,则需要另外添加方法
     */
    private boolean serviceMethod;


    /**
     * 如果不是mq本家的方法,则如何做
     */
    private Function<byte[], byte[]> function;

    MqttControlPacketTypeEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
        this.serviceMethod = true;
    }

    MqttControlPacketTypeEnum(byte code, String msg, Function<byte[], byte[]> function) {
        this.code = code;
        this.msg = msg;
        this.serviceMethod = false;
        this.function = function;
    }


    public static MqttControlPacketTypeEnum parsing(byte code) throws MqttControlPacketTypeNotFoundException {
        switch (code) {
            case 1:
                return CONNECT;
            case 2:
                return CONNACK;
            case 3:
                return PUBLISH;
            case 4:
                return PUBACK;
            case 5:
                return PUBREC;
            case 6:
                return PUBREL;
            case 7:
                return PUBCOMP;
            case 8:
                return SUBSCRIBE;
            case 9:
                return SUBACK;
            case 10:
                return UNSUBSCRIBE;
            case 11:
                return UNSUBACK;
            case 12:
                return PINGREQ;
            case 13:
                return PINGRESP;
            case 14:
                return DISCONNECT;
            default:
                throw new MqttControlPacketTypeNotFoundException(code);
        }
    }

    public boolean isFunction() {
        return this.function != null;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(boolean serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public Function<byte[], byte[]> getFunction() {
        return function;
    }

    public void setFunction(Function<byte[], byte[]> function) {
        this.function = function;
    }
}
