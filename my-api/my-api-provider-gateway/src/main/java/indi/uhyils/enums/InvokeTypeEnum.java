package indi.uhyils.enums;

import indi.uhyils.util.Asserts;
import java.util.Objects;

/**
 * 执行类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 10时22分
 */
public enum InvokeTypeEnum {
    /**
     *
     */
    HTTP(1),
    MYSQL(2),
    RPC(3),
    ;


    private final Integer code;

    InvokeTypeEnum(Integer code) {
        this.code = code;
    }

    /**
     * 解析调用类型
     *
     * @param code
     *
     * @return
     */
    public static InvokeTypeEnum parse(Integer code) {

        for (InvokeTypeEnum value : values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        Asserts.throwException("未找到调用方法");
        return null;
    }

    public Integer getCode() {
        return code;
    }
}
