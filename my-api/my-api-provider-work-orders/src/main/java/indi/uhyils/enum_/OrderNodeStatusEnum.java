package indi.uhyils.enum_;

/**
 * 节点状态
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月14日 18时35分
 */
public enum OrderNodeStatusEnum {
    /**
     * 作用同name
     */
    NO_START("未开始", 0),
    WAIT_STATUS("等待开始", 1),
    IN_START("处理中", 2),
    OVER("结束", 3),
    FAULT("失败", 4),
    TRANSFER("转交中", 6),
    TRANSFERRED("已转交", 7);

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private Integer code;

    OrderNodeStatusEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public static OrderNodeStatusEnum parse(Integer status) {
        for (OrderNodeStatusEnum value : values()) {
            if (value.code.equals(status)) {
                return value;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
