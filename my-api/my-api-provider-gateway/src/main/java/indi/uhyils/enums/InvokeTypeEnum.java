package indi.uhyils.enums;

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

    public Integer getCode() {
        return code;
    }
}
