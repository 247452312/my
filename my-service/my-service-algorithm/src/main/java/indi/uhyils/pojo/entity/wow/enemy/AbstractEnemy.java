package indi.uhyils.pojo.entity.wow.enemy;

import indi.uhyils.enums.SkillHurtTypeEnum;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import java.util.Map;

/**
 * 怪物
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 17时12分
 */
public abstract class AbstractEnemy extends AbstractEntity<Identifier> implements Enemy {

    /**
     * 最大血量
     */
    protected final Double maxBlood;

    /**
     * 最大法力值
     */
    protected final Double maxMana;

    /**
     * 各个属性抗性
     */
    protected final Map<SkillHurtTypeEnum, Double> resistance;

    /**
     * 护甲
     */
    protected final Double armor;

    /**
     * 等级
     */
    protected final Integer level;

    public AbstractEnemy(Double maxBlood, Double maxMana, Map<SkillHurtTypeEnum, Double> resistance, Double armor, Integer level) {
        this.maxBlood = maxBlood;
        this.maxMana = maxMana;
        this.resistance = resistance;
        this.armor = armor;
        this.level = level;
    }

    @Override
    public Integer level() {
        return level;
    }
}
