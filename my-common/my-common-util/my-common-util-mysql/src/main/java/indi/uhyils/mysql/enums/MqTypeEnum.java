package indi.uhyils.mysql.enums;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月19日 08时30分
 */
public enum MqTypeEnum {
    /**
     * rocket_mq
     */
    ROCKET_MQ(0),
    /**
     * rabbit_mq
     */
    RABBIT_MQ(1);

    private final Integer code;

    MqTypeEnum(Integer code) {
        this.code = code;
    }

    public static MqTypeEnum parse(Integer type) {
        for (MqTypeEnum value : values()) {
            if (Objects.equals(value.getCode(), type)) {
                return value;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }


}
