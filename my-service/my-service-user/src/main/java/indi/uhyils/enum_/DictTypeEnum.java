package indi.uhyils.enum_;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 21时03分
 */
public enum DictTypeEnum {
    INTEGER(0, "整数"),
    STRING(1, "字符串");

    /**
     * 编码
     */
    private Integer code;

    /**
     * 名称
     */
    private String name;

    DictTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
