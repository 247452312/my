package indi.uhyils.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 16时46分
 */
public enum SkillHurtTypeEnum {

    /**
     * 部位名称同name
     */
    NULL(0, "物理"),
    ICE(1, "冰"),
    FIRE(2, "火"),
    ARCANE(3, "奥"),
    LIGHT(4, "光"),
    DARK(5, "暗"),
    ;


    private final String name;

    private final Integer code;

    SkillHurtTypeEnum(Integer code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }
}
