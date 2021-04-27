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
     * 服务端可以收到的类型
     */
    CONNECT((byte) 1, "客户端请求连接服务端", true, bytes -> bytes),
    /**
     * 这是一个特殊的,只有他存在多种情况,所以需要特殊情况特殊分析
     */
    PUBLISH((byte) 3, "发布消息", true, "", new String[]{}),

    SUBSCRIBE((byte) 8, "客户端订阅请求", true, "", new String[]{}),
    UNSUBSCRIBE((byte) 10, "客户端取消订阅请求", true,"",new String[]{}),
    PINGREQ((byte) 12, "心跳请求", true,bytes -> bytes),


    CONNACK((byte) 2, "连接报文确认", true, bytes -> bytes),
    PUBACK((byte) 4, "QoS 1消息发布收到确认", true, bytes -> bytes),
    PUBREC((byte) 5, "发布收到（保证交付第一步）", true, bytes -> bytes),
    PUBREL((byte) 6, "发布释放（保证交付第二步）", true, bytes -> bytes),
    PUBCOMP((byte) 7, "QoS 2消息发布完成（保证交互第三步）", true, bytes -> bytes),
    SUBACK((byte) 9, "订阅请求报文确认", true,bytes -> bytes),
    UNSUBACK((byte) 11, "取消订阅报文确认", true,bytes -> bytes),
    PINGRESP((byte) 13, "心跳响应", true,bytes -> bytes),
    DISCONNECT((byte) 14, "客户端断开连接", false,bytes -> bytes);


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
     * 是否需要长连接
     */
    private boolean keepAlive;

    /**
     * 如果不是mq本家的方法,则如何做
     */
    private Function<byte[], byte[]> function;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数类型
     */
    private String[] paramsType;

    MqttControlPacketTypeEnum(byte code, String msg, boolean keepAlive, String methodName, String[] paramsType) {
        this.code = code;
        this.msg = msg;
        this.serviceMethod = true;
        this.keepAlive = keepAlive;
        this.methodName = methodName;
        this.paramsType = paramsType;
    }

    MqttControlPacketTypeEnum(byte code, String msg, boolean keepAlive, Function<byte[], byte[]> function) {
        this.code = code;
        this.msg = msg;
        this.serviceMethod = false;
        this.keepAlive = keepAlive;
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

    public static MqttControlPacketTypeEnum parsing(int code) throws MqttControlPacketTypeNotFoundException {
        return parsing((byte) code);
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

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getParamsType() {
        return paramsType;
    }

    public void setParamsType(String[] paramsType) {
        this.paramsType = paramsType;
    }
}
