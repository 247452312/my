package indi.uhyils.convert;

import indi.uhyils.entity.BaseEntity;
import indi.uhyils.pojo.model.base.BaseIdDO;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 15时57分
 */
public interface BaseEntityDOConvert<T extends BaseEntity, E extends BaseIdDO> {

    /**
     * entity转do
     *
     * @param entity
     *
     * @return
     */
    E entityToDo(T entity);

    /**
     * do转entity
     *
     * @param idDO
     *
     * @return
     */
    T doToEntity(E idDO);

}
