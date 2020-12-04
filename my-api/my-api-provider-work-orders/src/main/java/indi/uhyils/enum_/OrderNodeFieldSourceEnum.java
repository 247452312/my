package indi.uhyils.enum_;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月04日 08时15分
 */
public enum OrderNodeFieldSourceEnum {

    /**
     * 同name
     */
    DICT("字典", 1),
    USER("用户", 2),
    ORDER("工单", 3);


    private String name;
    private Integer code;

    OrderNodeFieldSourceEnum(String name, Integer code) {
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
