package indi.uhyils.pojo.entity.wow.skill;

import indi.uhyils.enums.SkillHurtTypeEnum;
import indi.uhyils.enums.SkillReleaseTypeEnum;
import indi.uhyils.pojo.entity.base.BaseEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.wow.Attributes;
import indi.uhyils.pojo.entity.wow.attack.Attack;
import indi.uhyils.pojo.entity.wow.buff.Buff;

/**
 * 技能
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月26日 10时27分
 */
public interface Skill extends BaseEntity<Identifier> {

    /**
     * 转换为没有结果buff增伤的原始伤害
     *
     * @return
     */
    Attack convertOriginalAttack(Attributes attributes);


    /**
     * 获取法术释放类型
     *
     * @return
     */
    SkillReleaseTypeEnum releaseType();

    /**
     * 伤害数值上限
     */
    Double maxDamageValue();

    /**
     * 伤害数值下限
     */
    Double minDamageValue();

    /**
     * 转换为buff
     *
     * @return
     */
    Buff convertBuff();


    /**
     * 引导时间,单位(毫秒)
     *
     * @return
     */
    Long bootTime();

    /**
     * 持续时间,单位(毫秒)
     *
     * @return
     */
    Long durationTime();

    /**
     * 是否触发公共cd
     *
     * @return
     */
    Boolean haveGCD();

    /**
     * 法术伤害类型
     *
     * @return
     */
    SkillHurtTypeEnum hurtType();


    /**
     * 是否一元法术
     *
     * @return
     */
    Boolean isUnary();

    /**
     * 是否aoe
     *
     * @return
     */
    Boolean isAoe();
}
