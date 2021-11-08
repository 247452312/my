package indi.uhyils.util.mapstruct;

import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import org.mapstruct.Named;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月13日 08时49分
 */
public final class EntityAndDoUtil {

    /**
     * entity转DO
     *
     * @param entity
     * @param <DO>
     * @param <ENTITY>
     *
     * @return
     */
    @Named("entityToDo")
    public static <DO extends BaseDO, ENTITY extends AbstractDoEntity<DO>> BaseDO toDo(ENTITY entity) {
        return entity.toData();
    }


}
