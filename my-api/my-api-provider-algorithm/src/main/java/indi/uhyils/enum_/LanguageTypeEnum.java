package indi.uhyils.enum_;

import java.util.Objects;

/**
 * 代码语言类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月17日 09时24分
 */
public enum LanguageTypeEnum {
    /**
     * java
     */
    JAVA(1, "java"),
    /**
     * python
     */
    PYTHON(2, "python");

    /**
     * 代码语言类型编码
     */
    private final Integer code;

    /**
     * 名称
     */
    private final String name;

    LanguageTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 解析code, 默认为java
     *
     * @param code
     *
     * @return
     */
    public static LanguageTypeEnum parse(Integer code) {
        for (LanguageTypeEnum value : values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        return JAVA;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
