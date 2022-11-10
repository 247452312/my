package indi.uhyils.pojo.entity.wow.equipment;

import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.wow.attributes.Attributes;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年11月09日 16时22分
 */
public abstract class AbstractEquipment extends AbstractEntity<Identifier> implements Equipment {

    protected final Attributes attributes;

    protected AbstractEquipment(Attributes attributes) {
        this.attributes = attributes;
    }


    @Override
    public Attributes attributes() {
        return attributes;
    }
}
