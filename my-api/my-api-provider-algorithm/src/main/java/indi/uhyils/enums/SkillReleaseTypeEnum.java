package indi.uhyils.enums;

/**
 * 技能类型枚举
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 16时39分
 */
public enum SkillReleaseTypeEnum {
    /**
     * 意义同name
     */
    STANDARD(1, "标准法术"),
    PROMPT(2, "瞬发法术"),
    DOT(3, "持续法术"),
    GUIDE(4, "引导法术"),
    ;

    private final Integer code;

    private final String name;

    SkillReleaseTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
