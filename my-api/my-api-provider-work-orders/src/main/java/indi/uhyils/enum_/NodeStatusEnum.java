package indi.uhyils.enum_;

/**
 * 节点状态 0->未开始 1->等待开始 2->处理中 3->结束 4->失败
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月14日 18时35分
 */
public enum NodeStatusEnum {
    /**
     * 作用同name
     */
    NO_START("未开始", 0),
    WAIT_STATUS("等待开始", 1),
    IN_START("处理中", 2),
    OVER("结束", 3),
    FAULT("失败", 4);

    NodeStatusEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 名称
     */
    private String name;
    /**
     * 代码
     */
    private Integer code;


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
