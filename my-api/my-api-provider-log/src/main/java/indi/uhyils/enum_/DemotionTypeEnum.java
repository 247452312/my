package indi.uhyils.enum_;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月28日 15时31分
 */
public enum DemotionTypeEnum {
    /**
     * 无操作
     */
    NULL(-1),
    /**
     * 降级
     */
    DOWN(0),
    /**
     * 恢复
     */
    RECOVER(1);

    private final Integer code;

    DemotionTypeEnum(Integer code) {
        this.code = code;
    }


    public Integer getCode() {
        return code;
    }
}
