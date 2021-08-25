package indi.uhyils.util;

import indi.uhyils.pojo.DO.base.BaseDoDO;
import indi.uhyils.pojo.DO.base.BaseIdDO;
import indi.uhyils.pojo.DTO.BaseDTO;
import indi.uhyils.pojo.entity.AbstractDoEntity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 21时25分
 */
public final class DtoEntityConvertUtil {

    private DtoEntityConvertUtil() {
    }

    /**
     * entity转DTO
     *
     * @param entity
     * @param targetClazz
     * @param <T>
     *
     * @return
     */
    public static <T extends BaseDTO, E extends BaseDoDO> BaseDTO toDto(AbstractDoEntity<E> entity, Class<T> targetClazz) {
        return BeanUtil.copyProperties(entity.toDo(), targetClazz);
    }

    public static <Y extends BaseIdDO, T extends AbstractDoEntity<Y>> T toEntity(Object dto, Class<Y> targetEntityDoClass, Class<T> entityClass) {
        try {
            Y e = BeanUtil.copyProperties(dto, targetEntityDoClass);
            Constructor<T> constructor = entityClass.getConstructor(targetEntityDoClass);
            return constructor.newInstance(e);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LogUtil.error(e);
            return null;
        }
    }
}
