package indi.uhyils.enum_;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月19日 14时12分
 */
public enum ConsumerStatusEnum {
    /**
     * 申请中
     */
    REGISTTING(0),
    /**
     * 使用中
     */
    USING(1),
    /**
     * 停用
     */
    DEACTIVATE(2);

    private final Integer code;

    ConsumerStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
