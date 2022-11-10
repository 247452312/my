package indi.uhyils.pojo.entity.wow.equipment;

import indi.uhyils.enums.PositionTypeEnum;
import indi.uhyils.pojo.entity.wow.attributes.Attributes;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年11月09日 16时30分
 */
public class LiquidGoggles extends AbstractEquipment {

    public LiquidGoggles() {
        super(new Attributes(80L, 90L, 0L, 100L, 40L, 30L));
    }

    @Override
    public PositionTypeEnum positionType() {
        return PositionTypeEnum.HEAD;
    }

    @Override
    public String name() {
        return "液化护目镜";
    }
}
