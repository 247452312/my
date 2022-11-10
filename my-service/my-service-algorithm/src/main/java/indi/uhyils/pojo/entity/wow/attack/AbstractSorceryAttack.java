package indi.uhyils.pojo.entity.wow.attack;

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
        final Boolean unary = skill.isUnary();
        return RandomUtils.nextDouble(minDamageValue, maxDamageValue);
    }
}
