package indi.uhyils.enum_;

/**
 * 订单状态
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 11时36分
 */
public enum OrderStatusEnum {
    /**
     * 同name
     */
    STOP("停用", 0),
    START("启动", 1),
    WITHDRAWING("撤回中", 2),
    WITHDRAWED("已撤回", 3),
    STOPING("停用中", 4),
    BACKING("回退中", 5);

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
