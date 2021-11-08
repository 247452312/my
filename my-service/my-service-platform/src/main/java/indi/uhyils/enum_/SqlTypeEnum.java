package indi.uhyils.enum_;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月26日 08时53分
 */
public enum SqlTypeEnum {
    /**
     * 意义同msg
     */
    SELECT(0, "查询"),
    UPDATE(1, "修改"),
    INSERT(2, "添加"),
    DELETE(3, "删除");

    private final Integer code;

    private final String msg;

    SqlTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static SqlTypeEnum parse(int code) {
        for (SqlTypeEnum value : values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
