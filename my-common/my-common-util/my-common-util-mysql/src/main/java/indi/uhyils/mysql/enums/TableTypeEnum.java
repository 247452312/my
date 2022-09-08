package indi.uhyils.mysql.enums;

/**
 * 表类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月08日 09时33分
 */
public enum TableTypeEnum {
    /**
     * 基本表
     */
    BASE_TABLE("BASE_TABLE"),
    /**
     * 系统视图
     */
    SYSTEM_VIEW("SYSTEM_VIEW"),
    /**
     * 视图
     */
    VIEW("VIEW"),
    ;


    private final String code;


    TableTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
