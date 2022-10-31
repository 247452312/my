package indi.uhyils.pojo.entity.wow;

import indi.uhyils.enums.PositionTypeEnum;
import indi.uhyils.pojo.entity.base.BaseEntity;
import indi.uhyils.pojo.entity.type.Identifier;

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
}
