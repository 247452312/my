package indi.uhyils.pojo.entity.wow;

import indi.uhyils.pojo.entity.wow.attack.Attack;
import indi.uhyils.pojo.entity.wow.attributes.Attributes;
import indi.uhyils.pojo.entity.wow.enemy.DemoEnemy;
import indi.uhyils.pojo.entity.wow.enemy.Enemy;
import indi.uhyils.pojo.entity.wow.equipment.LiquidGoggles;
import indi.uhyils.pojo.entity.wow.person.AbstractPerson;
import indi.uhyils.pojo.entity.wow.person.Person;
import indi.uhyils.pojo.entity.wow.skill.Frostbolt;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 09时28分
 */
public class WowTest {

    @Test
    public void attackTest() {
        Person person = new MyPerson();
        // 初始化属性
        person.initAttributes(new Attributes(1000L, 2000L, 700L, 400L, 300L, 289L));
        // 初始化天赋
        person.initTalent(Collections.emptyList());
        // 初始化装备
        person.initEquipment(Collections.singletonList(new LiquidGoggles()));
        // 初始化buff
        person.initBuff(Collections.emptyList());

        final Attack attack = person.castSkill(new Frostbolt());
        final Map<Enemy, Double> enemyIntegerMap = attack.hartEnemy(Collections.singletonList(new DemoEnemy(1.2 * 10000000, 1000D, new HashMap<>(), 14000D, 82)));
        for (Entry<Enemy, Double> entry : enemyIntegerMap.entrySet()) {
            final Enemy key = entry.getKey();
            final Double value = entry.getValue();
            System.out.println(key.name() + " : " + value);
        }
    }

    /**
     *
     */
    private static class MyPerson extends AbstractPerson {

    }

}
