package indi.uhyils.enum_;

import java.util.Objects;

/**
 * 资源类型
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月17日 12时35分
 */
public enum SourceTypeEnum {
    /**
     *
     */
    DB(0, "数据库"),
    HTTP(1, "http"),
    MQ(2, "mq");

    /**
     * 类型
     */
    private final Integer code;

    /**
     * 类型
     */
    private final String name;

    SourceTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SourceTypeEnum parse(Integer type) {
        for (SourceTypeEnum value : values()) {
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
