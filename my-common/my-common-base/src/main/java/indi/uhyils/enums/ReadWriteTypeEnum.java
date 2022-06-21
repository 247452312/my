package indi.uhyils.enums;

/**
 * 标识是读还是写
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 07时57分
 */
public enum ReadWriteTypeEnum {
    /**
     * 读
     */
    READ(1),
    /**
     * 写
     */
    WRITE(2);

    private final Integer code;

    ReadWriteTypeEnum(Integer code) {
        this.code = code;
    }

    /**
     * 解析code
     *
     * @param code
     *
     * @return
     */
    public static ReadWriteTypeEnum parse(Integer code) {
        for (ReadWriteTypeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }
}
