package indi.uhyils.netty.finder.mqtt;

import indi.uhyils.exception.MqttQosNotFoundException;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月29日 10时29分
 */
public enum MqttQosEnum {
    /**
     * 至多一次
     */
    AT_MOST_ONCE(0),
    /**
     * 至少一次
     */
    AT_LEAST_ONCE(1),
    /**
     * 确保只有一次
     */
    EXACTLY_ONCE(2);

    private Integer code;


    MqttQosEnum(Integer code) {
        this.code = code;
    }

    public static MqttQosEnum parsing(int code) throws MqttQosNotFoundException {
        switch (code) {
            case 0:
                return AT_MOST_ONCE;
            case 1:
                return AT_LEAST_ONCE;
            case 2:
                return EXACTLY_ONCE;
            default:
                throw new MqttQosNotFoundException(code);
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
