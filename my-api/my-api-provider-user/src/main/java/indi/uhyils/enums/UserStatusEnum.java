package indi.uhyils.enums;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月08日 19时06分
 */
public enum UserStatusEnum {
    /**
     * 同name
     */
    APPLYING(0, "申请中"),
    USING(1, "使用中"),
    STOPED(2, "已停用");

    private final Integer code;

    private final String name;

    UserStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public static UserStatusEnum parse(Integer code) {
        for (UserStatusEnum value : values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }
}
