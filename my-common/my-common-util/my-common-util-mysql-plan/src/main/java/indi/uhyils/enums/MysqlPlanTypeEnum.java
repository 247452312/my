package indi.uhyils.enums;

/**
 * 执行计划类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月01日 16时19分
 */
public enum MysqlPlanTypeEnum {
    /**
     * 意义同name
     */
    QUERY_NODE(1, "查询节点"),
    UPDATE_NODE(2, "查询节点"),

    COMPOSITE_NODE(3, "组合节点");

    private final Integer code;

    private final String name;

    MysqlPlanTypeEnum(Integer code, String name) {
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
