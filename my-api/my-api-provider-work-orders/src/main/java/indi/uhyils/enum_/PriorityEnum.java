package indi.uhyils.enum_;

/**
 * 优先级
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 11时32分
 */
public enum PriorityEnum {
    /**
     * 同name
     */
    NORMAL("正常", 0),
    URGENT("紧急", 1);


    private String name;
    private Integer code;

    PriorityEnum(String name, Integer code) {
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

    public static PriorityEnum parse(Integer code) {
        switch (code) {
            case 0:
                return NORMAL;
            case 1:
                return URGENT;
            default:
                return null;
        }

    }
}
