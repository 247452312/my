package indi.uhyils.enums;

import java.util.Objects;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月21日 08时46分
 */
public enum UserTypeEnum {
    /**
     * 注意 code必须要两位
     */
    VISITER("01", "游客"),
    USER("02", "用户");

    private final String code;

    private final String name;

    UserTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Optional<UserTypeEnum> getByCode(String code) {
        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            if (Objects.equals(userTypeEnum.getCode(), code)) {
                return Optional.of(userTypeEnum);
            }
        }
        return Optional.empty();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
