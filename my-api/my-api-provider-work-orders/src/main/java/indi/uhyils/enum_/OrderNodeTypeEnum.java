package indi.uhyils.enum_;

/**
 * 节点类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 11时32分
 */
public enum OrderNodeTypeEnum {
    /**
     * 同name
     */
    START("开始节点", 0),
    INTERMEDIATE("中间节点", 1),
    END("结束节点", 2);


    private String name;

    private Integer code;

    OrderNodeTypeEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public static OrderNodeTypeEnum parse(Integer type) {
        for (OrderNodeTypeEnum value : values()) {
            if (value.code.equals(type)) {
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
