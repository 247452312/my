package indi.uhyils.enum_;

/**
 * redis 添加回复请求
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月14日 13时45分
 */
public enum RedisAddEnum {
    /**
     * 添加成功
     */
    SUCCESS(1),
    /**
     * 有重复
     */
    HAVE_KEY(2);

    private Integer type;

    RedisAddEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
