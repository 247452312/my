package indi.uhyils.enum_;

import java.util.Objects;

/**
 * 资源类型
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月17日 12时35分
 */
public enum InterfaceTypeEnum {
    /**
     *
     */
    DB(0, "数据库"),
    MQ(1, "mq"),
    HTTP(2, "http");

    /**
     * 类型
     */
    private final Integer code;

    /**
     * 类型
     */
    private final String name;

    InterfaceTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static InterfaceTypeEnum parse(Integer type) {
        for (InterfaceTypeEnum value : values()) {
            if (Objects.equals(type, value.getCode())) {
                return value;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
