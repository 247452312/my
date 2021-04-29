package indi.uhyils.exception;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月29日 10时35分
 */
public class MqttQosNotFoundException extends UserException {


    public MqttQosNotFoundException(int code) {
        super("MQTT传输时QOS没有找到: " + code);
    }
}
