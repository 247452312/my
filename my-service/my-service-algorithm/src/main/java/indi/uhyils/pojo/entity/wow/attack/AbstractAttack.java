package indi.uhyils.pojo.entity.wow.attack;

import indi.uhyils.enums.SkillReleaseTypeEnum;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.wow.Attributes;
import indi.uhyils.pojo.entity.wow.skill.Skill;
import indi.uhyils.util.Asserts;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 17时02分
 */
public abstract class AbstractAttack extends AbstractEntity<Identifier> implements Attack {

    /**
     * 标准dot持续时间
     */
    private static final long DOT_DURATION_STANDARD = 15000;

    /**
     * 标准法术引导时间
     */
    private static final long BOOT_DURATION_STANDARD = 3500;

    /**
     * 附加伤害
     */
    protected List<Attack> otherAttack;

    protected Skill skill;

    protected Attributes attributes;

    /**
     * 伤害数值上限
     */
    protected Double maxDamageValue;

    /**
     * 伤害数值下限
     */
    protected Double minDamageValue;

    protected AbstractAttack(Skill skill, Attributes attributes) {
        this.skill = skill;
        this.attributes = attributes;

        // 初始化伤害
        initDamage();
    }

    /**
     * 根据技能和属性初始化伤害
     */
    protected void initDamage() {
        // 最大
        final Double maxDamageValue = skill.maxDamageValue();
        // 最小
        final Double minDamageValue = skill.minDamageValue();

        // 法强
        final long spellPower = attributes.spellPower();

        final SkillReleaseTypeEnum skillReleaseTypeEnum = skill.releaseType();
        double releaseCoefficient = 1;
        switch (skillReleaseTypeEnum) {
            case DOT:
                releaseCoefficient = skill.durationTime() * 1.0 / DOT_DURATION_STANDARD;
                break;
            case GUIDE:
            case STANDARD:
                releaseCoefficient = skill.bootTime() / BOOT_DURATION_STANDARD;
                break;
            case PROMPT:
                releaseCoefficient = 0.5;
                break;
            default:
                Asserts.throwException("未找到法术释放类型");
        }

        if (skill.isAoe()) {
            releaseCoefficient /= 3.0;
        }
        if (Boolean.FALSE.equals(skill.isUnary())) {
            releaseCoefficient *= 0.95;
        }
        this.maxDamageValue = maxDamageValue + spellPower * releaseCoefficient;
        this.minDamageValue = minDamageValue + spellPower * releaseCoefficient;


    }

    protected AbstractAttack(Skill skill, Double maxDamageValue, Double minDamageValue) {
        this.skill = skill;
        this.maxDamageValue = maxDamageValue;
        this.minDamageValue = minDamageValue;
    }

    @Override
    public Skill basedOnSkill() {
        return skill;
    }

    @Override
    public List<Attack> otherAttack() {
        return otherAttack;
    }

}
