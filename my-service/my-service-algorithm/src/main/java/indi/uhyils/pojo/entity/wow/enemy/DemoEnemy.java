package indi.uhyils.pojo.entity.wow.enemy;

import indi.uhyils.enums.SkillHurtTypeEnum;
import java.util.Map;

/**
 * demo怪物
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年11月04日 15时31分
 */
public class DemoEnemy extends AbstractEnemy {

    public DemoEnemy(Double maxBlood, Double maxMana, Map<SkillHurtTypeEnum, Double> resistance, Double armor, Integer level) {
        super(maxBlood, maxMana, resistance, armor, level);
    }

    @Override
    public String name() {
        return "测试怪";
    }
}
