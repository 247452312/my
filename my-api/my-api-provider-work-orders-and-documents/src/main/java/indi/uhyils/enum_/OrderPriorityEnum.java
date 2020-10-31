package indi.uhyils.enum_;

/**
 * 订单优先级
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月28日 08时35分
 */
public enum OrderPriorityEnum {
    /**
     * 一般
     */
    normal("一般", 0),
    /**
     * 紧急
     */
    urgent("紧急", 1);

    private String name;
    private Integer code;

    OrderPriorityEnum(String name, Integer code) {
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
