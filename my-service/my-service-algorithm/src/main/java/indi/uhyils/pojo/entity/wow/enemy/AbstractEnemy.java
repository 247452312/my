package indi.uhyils.pojo.entity.wow.enemy;

import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.pojo.entity.type.Identifier;

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

    protected final Double

    public AbstractEnemy(Double maxBlood, Double maxMana) {
        this.maxBlood = maxBlood;
        this.maxMana = maxMana;
    }


}
