package indi.uhyils.mysql.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月17日 14时24分
 */
public enum DbTypeEnum {
    /**
     * 数据库类型
     */
    MYSQL(0, "mysql"),
    ORACLE(1, "oracle");


    private final Integer code;

    private final String name;

    DbTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static DbTypeEnum parse(Integer type) {
        for (DbTypeEnum value : values()) {
            if (value.getCode().equals(type)) {
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
