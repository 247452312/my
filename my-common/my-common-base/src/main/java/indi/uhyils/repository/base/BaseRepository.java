package indi.uhyils.repository.base;

import indi.uhyils.entity.BaseEntity;
import indi.uhyils.entity.HaveIdEntity;
import indi.uhyils.entity.query.BaseOrder;
import indi.uhyils.type.Identifier;
import java.util.List;

/**
 * 基础仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 14时49分
 */
public interface BaseRepository<T extends BaseEntity> {

    /**
     * 保存逻辑
     *
     * @param entity
     *
     * @return id
     */
    Identifier save(T entity);

    /**
     * 批量报错或修改
     *
     * @param entities
     *
     * @return
     */
    List<Identifier> save(List<T> entities);

    /**
     * 根据id查询
     *
     * @param id 主键id
     *
     * @return
     */
    T find(HaveIdEntity id);

    /**
     * 根据id查询
     *
     * @param id 主键id
     *
     * @return
     */
    List<T> find(BaseOrder id);

    /**
     * 批量删除
     *
     * @param ids
     *
     * @return
     */
    int remote(List<T> ids);

}
