package indi.uhyils.pojo.entity.wow;

import indi.uhyils.pojo.entity.base.BaseEntity;
import indi.uhyils.pojo.entity.type.Identifier;

/**
 * buff
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月26日 10时28分
 */
public interface Buff extends BaseEntity<Identifier> {

    /**
     * 作用于一个技能
     *
     * @param attack
     *
     * @return
     */
    Attack actOnAttack(Attack attack);
}
