package indi.uhyils.plan.enums;

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
    EXECUTE_SQL(1, "执行sql节点"),
    MAPPING(2, "字段映射节点"),
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
