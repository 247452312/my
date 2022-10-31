package indi.uhyils.enums;

/**
 * 部位类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 09时46分
 */
public enum PositionTypeEnum {
    /**
     * 部位名称同name
     */
    HEAD(1, "头部"),
    ;


    private final String name;

    private final Integer code;

    PositionTypeEnum(Integer code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
}
