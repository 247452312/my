package indi.uhyils.enum_;

/**
 * kpro创建的时候传入的数据库类型,通过prase方法可以变换为现有的DbType
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 07时05分
 */
public enum DbTypeEnum {
    /**
     * 同名字 我不信有人认不出来
     */
    MYSQL(1),
    ORACLE(2),
    SQLITE(3);

    private Integer typeCode;

    DbTypeEnum(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public static DbTypeEnum prase(Integer code) {
        switch (code) {
            case 1:
                return MYSQL;
            case 2:
                return ORACLE;
            case 3:
                return SQLITE;
            default:
                return null;
        }
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }
}
