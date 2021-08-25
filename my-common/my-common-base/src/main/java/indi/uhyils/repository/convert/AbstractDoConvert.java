package indi.uhyils.repository.convert;

import indi.uhyils.pojo.entity.AbstractDoEntity;
import indi.uhyils.pojo.DO.base.BaseIdDO;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时23分
 */
public abstract class AbstractDoConvert<T extends AbstractDoEntity<E>, E extends BaseIdDO> implements BaseEntityDOConvert<T, E> {

    @Override
    public E entityToDo(T entity) {
        return entity.toDo();
    }

}
