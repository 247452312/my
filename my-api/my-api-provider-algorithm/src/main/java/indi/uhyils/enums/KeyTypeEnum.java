package indi.uhyils.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月11日 08时14分
 */
public enum KeyTypeEnum {

    /**
     * 主键
     */
    KEY(1),
    /**
     * 非主键
     */
    NO_KEY(2);

    private Integer type;

    KeyTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
