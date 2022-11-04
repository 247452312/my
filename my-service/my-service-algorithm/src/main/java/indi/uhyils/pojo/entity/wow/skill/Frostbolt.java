package indi.uhyils.pojo.entity.wow.skill;

import indi.uhyils.enums.SkillHurtTypeEnum;
import indi.uhyils.enums.SkillReleaseTypeEnum;
import indi.uhyils.pojo.entity.wow.Attributes;
import indi.uhyils.pojo.entity.wow.attack.Attack;
import indi.uhyils.pojo.entity.wow.attack.RealAttack;
import indi.uhyils.pojo.entity.wow.buff.Buff;

/**
 * 寒冰箭
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年11月04日 14时16分
 */
public class Frostbolt extends AbstractSkill {

    @Override
    public Attack convertOriginalAttack(Attributes attributes) {
        return new RealAttack(this, attributes);
    }

    @Override
    public SkillReleaseTypeEnum releaseType() {
        return SkillReleaseTypeEnum.STANDARD;
    }

    @Override
    public Double maxDamageValue() {
        return 2000D;
    }

    @Override
    public Double minDamageValue() {
        return 1800D;
    }

    @Override
    public Buff convertBuff() {
        // 无buff
        return null;
    }

    @Override
    public Long bootTime() {
        // 2.5秒
        return 2500L;
    }

    @Override
    public Long durationTime() {
        return 0L;
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
        // 二元法术
        return Boolean.FALSE;
    }

    @Override
    public Boolean isAoe() {
        return Boolean.FALSE;
    }
}
