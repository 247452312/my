package indi.uhyils.pojo.entity.wow.equipment;

import indi.uhyils.enums.PositionTypeEnum;
import indi.uhyils.pojo.entity.base.BaseEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.wow.attributes.Attributes;

/**
 * 装备
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年10月26日 10时27分
 */
public interface Equipment extends BaseEntity<Identifier> {

    /**
     * 部位类型
     *
     * @return
     */
    PositionTypeEnum positionType();

    /**
     * 装备名称
     *
     * @return
     */
    String name();

    /**
     * 获取装备对应的属性
     *
     * @return
     */
    Attributes attributes();

}
