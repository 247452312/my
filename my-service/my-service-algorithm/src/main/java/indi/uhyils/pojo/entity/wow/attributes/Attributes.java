package indi.uhyils.pojo.entity.wow.attributes;

import indi.uhyils.pojo.entity.wow.equipment.Equipment;
import java.util.List;

/**
 * 属性
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 16时51分
 */
public final class Attributes {


    /**
     * 智力
     */
    protected final long intelligence;

    /**
     * 法强
     */
    protected final long spellPower;

    /**
     * 精神
     */
    protected final long spirit;

    /**
     * 急速
     */
    protected final long rapidly;

    /**
     * 暴击
     */
    protected final long crit;

    /**
     * 命中
     */
    protected final long hit;

    /**
     * 等级
     */
    protected final long level;

    private Attributes(long intelligence, long spellPower, long spirit, long rapidly, long crit, long hit, long level) {
        this.intelligence = intelligence;
        this.spellPower = spellPower;
        this.spirit = spirit;
        this.rapidly = rapidly;
        this.crit = crit;
        this.hit = hit;
        this.level = level;
    }


    public static Attributes buildEquipment(long intelligence, long spellPower, long spirit, long rapidly, long crit, long hit) {
        return build(intelligence, spellPower, spirit, rapidly, crit, hit, 0);
    }

    public static Attributes build(long intelligence, long spellPower, long spirit, long rapidly, long crit, long hit, long level) {
        return new Attributes(intelligence, spellPower, spirit, rapidly, crit, hit, level);
    }

    public static Attributes buildPerson(long intelligence, long spellPower, long spirit, long rapidly, long crit, long hit, long level) {
        return new Attributes(intelligence, spellPower, spirit, rapidly, crit, hit, level);
    }

    /**
     * 加上装备后的属性
     *
     * @return
     */
    public Attributes withEquipment(List<Equipment> equipments) {

        long intelligence = this.intelligence;

        long spellPower = this.spellPower;

        long spirit = this.spirit;

        long rapidly = this.rapidly;

        long crit = this.crit;

        long hit = this.hit;

        long level = this.level;

        for (Equipment equipment : equipments) {
            final Attributes attributes = equipment.attributes();
            intelligence += attributes.intelligence();
            spellPower += attributes.spellPower();
            spirit += attributes.spirit();
            rapidly += attributes.rapidly();
            crit += attributes.crit();
            hit += attributes.hit();
            level += attributes.level();
        }

        return new Attributes(intelligence, spellPower, spirit, rapidly, crit, hit, level);
    }

    public long intelligence() {
        return intelligence;
    }

    public long spellPower() {
        return spellPower;
    }

    public long spirit() {
        return spirit;
    }

    public long rapidly() {
        return rapidly;
    }

    public long crit() {
        return crit;
    }

    public long hit() {
        return hit;
    }

    public long level() {
        return level;
    }

}
