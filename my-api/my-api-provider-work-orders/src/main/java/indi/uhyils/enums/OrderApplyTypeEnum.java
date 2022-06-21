package indi.uhyils.enums;

/**
 * 订单申请类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月26日 17时45分
 */
public enum OrderApplyTypeEnum {
    /**
     * 同名称
     */
    TRANS("转交申请", 0);

    private String name;

    private Integer code;

    OrderApplyTypeEnum(String name, Integer code) {
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
