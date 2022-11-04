package indi.uhyils.pojo.entity.wow.person;

import indi.uhyils.pojo.entity.base.BaseEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.wow.Attributes;
import indi.uhyils.pojo.entity.wow.Equipment;
import indi.uhyils.pojo.entity.wow.skill.Skill;
import indi.uhyils.pojo.entity.wow.Talent;
import indi.uhyils.pojo.entity.wow.attack.Attack;
import indi.uhyils.pojo.entity.wow.buff.Buff;
import java.util.List;

/**
 * 人物
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月26日 10时27分
 */
public interface Person extends BaseEntity<Identifier> {

    /**
     * 属性
     *
     * @return
     */
    Attributes attributes();

    /**
     * 初始化buff
     *
     * @param buffs
     */
    void initBuff(List<Buff> buffs);

    /**
     * 初始化属性
     *
     * @param attributes
     */
    void initAttributes(Attributes attributes);

    /**
     * 添加buff
     *
     * @param buffs
     */
    void addBuff(Buff... buffs);

    /**
     * 移除buff
     *
     * @param buffs
     */
    void removeBuff(Buff... buffs);

    /**
     * 初始化装备
     *
     * @param equipments
     */
    void initEquipment(List<Equipment> equipments);

    /**
     * 更换装备
     *
     * @param equipments
     */
    void changeEquipment(Equipment... equipments);

    /**
     * 初始化天赋
     *
     * @param talents
     */
    void initTalent(List<Talent> talents);


    /**
     * 释放一个技能
     *
     * @param skill
     *
     * @return
     */
    Attack castSkill(Skill skill);


}
