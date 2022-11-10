package indi.uhyils.pojo.entity.wow.person;

import indi.uhyils.enums.PositionTypeEnum;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.wow.attack.Attack;
import indi.uhyils.pojo.entity.wow.attributes.Attributes;
import indi.uhyils.pojo.entity.wow.buff.Buff;
import indi.uhyils.pojo.entity.wow.equipment.Equipment;
import indi.uhyils.pojo.entity.wow.skill.Skill;
import indi.uhyils.pojo.entity.wow.Talent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 09时36分
 */
public abstract class AbstractPerson extends AbstractEntity<Identifier> implements Person {

    private Attributes attributes;

    /**
     * 人物buff
     */
    private List<Buff> buffs = new ArrayList<>();

    /**
     * 人物装备
     */
    private List<Equipment> equipments = new ArrayList<>();

    /**
     * 天赋
     */
    private List<Talent> talents = new ArrayList<>();

    @Override
    public Attributes attributes() {
        return attributes;
    }

    @Override
    public void initBuff(List<Buff> buffs) {
        buffs.clear();
        buffs.addAll(buffs);
    }

    @Override
    public void initAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public void addBuff(Buff... buffs) {
        this.buffs.addAll(Arrays.asList(buffs));
    }

    @Override
    public void removeBuff(Buff... buffs) {
        for (Buff buff : buffs) {
            this.buffs.remove(buff);
        }
    }

    @Override
    public void initEquipment(List<Equipment> equipments) {
        this.equipments.clear();
        this.equipments.addAll(equipments);
    }

    @Override
    public void changeEquipment(Equipment... equipments) {
        final Map<PositionTypeEnum, List<Equipment>> positionTypeEquipmentMap = this.equipments.stream().collect(Collectors.groupingBy(Equipment::positionType));
        for (Equipment equipment : equipments) {
            final PositionTypeEnum positionType = equipment.positionType();
            final List<Equipment> willChangeEquipment = positionTypeEquipmentMap.getOrDefault(positionType, Collections.emptyList());
            // todo 重新计算属性

        }
    }

    @Override
    public void initTalent(List<Talent> talents) {
        this.talents.addAll(talents);
    }

    @Override
    public Attack castSkill(Skill skill) {
        Attack attack = skill.convertOriginalAttack(attributes);
        for (Buff buff : buffs) {
            attack = buff.actOnAttack(attack);
        }
        buffs.add(skill.convertBuff());
        return attack;
    }
}
