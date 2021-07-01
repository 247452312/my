package indi.uhyils.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月26日 19时12分
 */
public class MqttControlPacketTypeNotFoundException extends UserException {
    public MqttControlPacketTypeNotFoundException(byte code) {
        super("MQTT报文 控制报文类型(MQTT Control Packet type)错误:" + code);
    }
}
