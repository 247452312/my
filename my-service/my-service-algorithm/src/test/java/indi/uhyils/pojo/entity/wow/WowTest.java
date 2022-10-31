package indi.uhyils.pojo.entity.wow;

import indi.uhyils.enums.SkillHurtTypeEnum;
import indi.uhyils.enums.SkillReleaseTypeEnum;
import indi.uhyils.pojo.entity.wow.person.AbstractPerson;
import indi.uhyils.pojo.entity.wow.skill.AbstractSkill;
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
        final Attack attack = person.castSkill(new Frostbolt());
        attack.hartEnemy(Arrays.asList());
    }

    /**
     *
     */
    private static class MyPerson extends AbstractPerson {

    }

    /**
     * 寒冰箭
     */
    private static class Frostbolt extends AbstractSkill {

        @Override
        public Attack convertOriginalAttack(Attributes attributes) {
            return null;
        }

        @Override
        public Buff convertBuff() {
            return null;
        }

        @Override
        public SkillReleaseTypeEnum releaseType() {
            return SkillReleaseTypeEnum.STANDARD;
        }

        @Override
        public Double maxDamageValue() {
            return null;
        }

        @Override
        public Double minDamageValue() {
            return null;
        }

        @Override
        public Long bootTime() {
            return 2500L;
        }

        @Override
        public Long duration() {
            return null;
        }

        @Override
        public Boolean haveGCD() {
            return Boolean.TRUE;
        }

        @Override
        public SkillHurtTypeEnum hurtType() {
            return SkillHurtTypeEnum.ICE;
        }

        @Override
        public Boolean isUnary() {
            return Boolean.FALSE;
        }

        @Override
        public Boolean isAoe() {
            return Boolean.FALSE;
        }
    }
}
