package indi.uhyils.pojo.entity.wow.attack;

import indi.uhyils.pojo.entity.wow.Attributes;
import indi.uhyils.pojo.entity.wow.Enemy;
import indi.uhyils.pojo.entity.wow.Skill;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 17时18分
 */
public class RealAttack extends AbstractAttack {


    protected RealAttack(Skill skill, Attributes attributes) {
        super(skill, attributes);
    }

    protected RealAttack(Skill skill, Double maxDamageValue, Double minDamageValue) {
        super(skill, maxDamageValue, minDamageValue);
    }

    @Override
    public Map<Enemy, Integer> hartEnemy(List<Enemy> enemies) {
        return null;
    }

}
