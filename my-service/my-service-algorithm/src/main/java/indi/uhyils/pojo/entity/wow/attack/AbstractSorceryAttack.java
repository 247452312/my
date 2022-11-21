package indi.uhyils.pojo.entity.wow.attack;

import indi.uhyils.enums.SkillHurtTypeEnum;
import indi.uhyils.pojo.entity.wow.attributes.Attributes;
import indi.uhyils.pojo.entity.wow.enemy.Enemy;
import indi.uhyils.pojo.entity.wow.skill.Skill;
import org.apache.commons.lang3.RandomUtils;

/**
 * 法术伤害
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年11月10日 14时05分
 */
public abstract class AbstractSorceryAttack extends AbstractAttack {

    protected AbstractSorceryAttack(Skill skill, Attributes attributes) {
        super(skill, attributes);
    }

    protected AbstractSorceryAttack(Skill skill, Double maxDamageValue, Double minDamageValue) {
        super(skill, maxDamageValue, minDamageValue);
    }

    @Override
    public Double hartEnemy(Enemy enemies) {
        // 最终结果
        double finalResult = 0;

        final Boolean unary = skill.isUnary();
        // 法术伤害公式计算 (被攻击怪物抗性/施法者等级*5) * 0.75 如果抗性大于目标等级*5 则结果恒为0.75
        // 二元法术计算为命中概率 一元法术计算为平均抵抗
        SkillHurtTypeEnum skillHurtTypeEnum = skill.hurtType();
        // 怪物依据自身等级后计算的抗性
        Integer resistance = enemies.resistance(skillHurtTypeEnum);

        // 抵抗概率
        final double resistanceProbability;

        final long fiveLevel = attributes.level() * 5;
        if (resistance > fiveLevel) {
            resistanceProbability = 0.75;
        } else {
            resistanceProbability = 0.75 * resistance / fiveLevel;
        }
        // 一元法术
        if (unary) {
            finalResult = RandomUtils.nextDouble(minDamageValue, maxDamageValue) * (1 - resistanceProbability);
        } else {
            finalResult = RandomUtils.nextDouble(0, 1) > 0.75 ? 0 : RandomUtils.nextDouble(minDamageValue, maxDamageValue);
        }

        // 暴击
        if (RandomUtils.nextDouble(0, 1) < attributes.crit() / 46.0) {
            finalResult = finalResult * skill.criticalMultiplier();
        }

        return finalResult;

    }
}
