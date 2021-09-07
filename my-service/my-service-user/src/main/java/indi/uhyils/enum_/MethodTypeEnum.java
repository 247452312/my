package indi.uhyils.enum_;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月28日 18时38分
 */
public enum MethodTypeEnum {
    /**
     * 读接口
     */
    READ(1),
    /**
     * 写接口
     */
    WRITE(2);

    private Integer type;

    MethodTypeEnum(Integer type) {
        this.type = type;
    }

    public static MethodTypeEnum parse(Integer methodType) {
        if (methodType == null) {
            return null;
        }

        for (MethodTypeEnum value : values()) {
            if (value.getType().equals(methodType)) {
                return value;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }
}
