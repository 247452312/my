package indi.uhyils.enums;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月08日 15时26分
 */
public enum HttpTypeEnum {
    /**
     *aa
     */
    GET(1, "GET"),
    POST(2, "POST"),
    DELETE(3, "DELETE"),
    UPDATE(4, "UPDATE"),
    ;

    private final Integer code;

    private final String name;

    HttpTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static HttpTypeEnum getByCode(Integer code) {
        for (HttpTypeEnum value : values()) {
            if (Objects.equals(value.getCode(), code)) {
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
