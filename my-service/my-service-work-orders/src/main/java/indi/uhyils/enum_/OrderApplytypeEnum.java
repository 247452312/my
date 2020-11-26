package indi.uhyils.enum_;

/**
 * 订单申请类型
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月26日 17时45分
 */
public enum OrderApplytypeEnum {
    ;

    private String name;
    private Integer code;

    OrderApplytypeEnum(String name, Integer code) {
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
