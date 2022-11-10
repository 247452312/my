package indi.uhyils.pojo.entity.wow.enemy;

import indi.uhyils.pojo.entity.base.BaseEntity;
import indi.uhyils.pojo.entity.type.Identifier;

/**
 * 敌人
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月26日 10时29分
 */
public interface Enemy extends BaseEntity<Identifier> {

    /**
     * 等级
     *
     * @return
     */
    Integer level();

    /**
     * 名称
     *
     * @return
     */
    String name();
}
