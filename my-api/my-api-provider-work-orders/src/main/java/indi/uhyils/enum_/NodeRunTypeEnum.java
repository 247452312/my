package indi.uhyils.enum_;

/**
 * 节点处理类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 11时32分
 */
public enum NodeRunTypeEnum {
    /**
     * 同name
     */
    AUTO("自动处理", 0),
    MANUAL("人工处理", 1);


    private String name;
    private Integer code;

    NodeRunTypeEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
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
