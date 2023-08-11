package indi.uhyils.enums;

import indi.uhyils.annotation.NotNull;
import indi.uhyils.exception.AssertException;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月08日 15时26分
 */
public enum HttpTypeEnum {
    /**
     * aa
     */
    GET(1, "GET"),
    POST(2, "POST"),
    DELETE(3, "DELETE"),
    UPDATE(4, "UPDATE"),
    PUT(5, "PUT"),
    HEAD(6, "HEAD"),
    OPTIONS(7, "OPTIONS"),
    TRACE(8, "TRACE"),
    ;

    private final Integer code;

    private final String name;

    HttpTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @NotNull
    public static HttpTypeEnum getByCode(Integer code) {
        for (HttpTypeEnum value : values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        throw new AssertException("httpType未找到");
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
