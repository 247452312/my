package indi.uhyils.pojo.entity.wow;

import java.util.List;

/**
 * 属性
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月28日 16时51分
 */
public interface Attributes {

    /**
     * 获取智力值
     *
     * @return
     */
    long intelligence();

    /**
     * 法强
     *
     * @return
     */
    long spellPower();

    /**
     * 精神
     *
     * @return
     */
    long spirit();

    /**
     * 急速
     *
     * @return
     */
    long rapidly();

    /**
     * 暴击
     *
     * @return
     */
    long crit();

    /**
     * 加上装备后的属性
     *
     * @return
     */
    Attributes withEquipment(List<Equipment> equipment);
}
