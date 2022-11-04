package indi.uhyils.pojo.entity.wow;

import indi.uhyils.pojo.entity.wow.attack.Attack;
import indi.uhyils.pojo.entity.wow.person.AbstractPerson;
import indi.uhyils.pojo.entity.wow.person.Person;
import indi.uhyils.pojo.entity.wow.skill.Frostbolt;
import java.util.Arrays;
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
        person.initAttributes();
        // 初始化天赋
        person.initTalent();
        // 初始化装备
        person.initEquipment();
        // 初始化buff
        person.initBuff();

        final Attack attack = person.castSkill(new Frostbolt());
        attack.hartEnemy(Arrays.asList(new ));
    }

    /**
     *
     */
    private static class MyPerson extends AbstractPerson {

    }

}
