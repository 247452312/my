package indi.uhyils.enums;

/**
 * 公司接口权限状态枚举
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 10时24分
 */
public enum CompanyPowerStatusEnum {
    /**
     *
     */
    DISABLE(-1, "禁用"),
    APPLING(0, "申请中"),
    NORMAL(1, "正常使用中"),
    ;

    private final Integer code;

    private final String name;

    CompanyPowerStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
