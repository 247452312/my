package indi.uhyils.pojo.entity.wow.attack;

import indi.uhyils.pojo.entity.wow.attributes.Attributes;
import indi.uhyils.pojo.entity.wow.skill.Skill;

/**
 * 伤害
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 17时18分
 */
public class RealAttack extends AbstractAttack {


    public RealAttack(Skill skill, Attributes attributes) {
        super(skill, attributes);
    }

    public RealAttack(Skill skill, Double maxDamageValue, Double minDamageValue) {
        super(skill, maxDamageValue, minDamageValue);
    }


}
