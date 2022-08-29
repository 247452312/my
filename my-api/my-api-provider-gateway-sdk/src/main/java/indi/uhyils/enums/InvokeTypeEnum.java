package indi.uhyils.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月29日 09时19分
 */
public enum InvokeTypeEnum {
    /**
     *
     */
    HTTP(1),
    MYSQL(2),
    RPC(3),
    ;

    /**
     * 类型编码
     */
    private final Integer code;


    InvokeTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
