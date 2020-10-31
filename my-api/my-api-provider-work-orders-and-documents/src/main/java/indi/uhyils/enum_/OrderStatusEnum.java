package indi.uhyils.enum_;

/**
 * 订单状态
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月28日 08时35分
 */
public enum OrderStatusEnum {
    /**
     * 未完成
     */
    undone("未完成", 0),
    /**
     * 已完成
     */
    done("已完成", 1);

    private String name;
    private Integer code;

    OrderStatusEnum(String name, Integer code) {
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
