package indi.uhyils.enum_;

/**
 * 节点属性类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 11时32分
 */
public enum NodeFieldTypeEnum {
    /**
     * 同name
     */
    INPUT("编辑框", 1),
    RADIO("单选框", 2),
    CHECKBOX("多选框", 3),
    SELECT("下拉框", 4),
    TEXT("文本框", 5);


    private String name;
    private Integer code;

    NodeFieldTypeEnum(String name, Integer code) {
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
