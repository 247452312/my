package indi.uhyils.enum_;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月14日 13时49分
 */
public enum RedisUpdateEnum {
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 没有key
     */
    NO_KEY(2);

    private Integer type;

    RedisUpdateEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
