package indi.uhyils.enum_;

/**
 * 订单申请状态
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月26日 17时45分
 */
public enum OrderApplyStatusEnum {
    /**
     * 同名称
     */
    NO_SEE("未查看", 0),
    NO_ACCEPT("未受理", 1),
    ACCEPTED("已受理", 2),
    AGREE("已同意", 3),
    REJECT("已驳回", 4);

    private String name;

    private Integer code;

    OrderApplyStatusEnum(String name, Integer code) {
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
