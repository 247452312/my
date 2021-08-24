package indi.uhyils.repository.base;

import indi.uhyils.entity.BaseEntity;
import indi.uhyils.entity.HaveIdEntity;
import indi.uhyils.entity.query.BaseOrder;
import indi.uhyils.entity.type.Identifier;
import indi.uhyils.pojo.response.base.Page;
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
    T find(Identifier id);

    /**
     * 根据条件查询
     *
     * @param order 主键id
     *
     * @return
     */
    List<T> findNoPage(BaseOrder order);

    /**
     * 根据条件分页查询
     *
     * @param order 主键id
     *
     * @return
     */
    Page<T> find(BaseOrder order);


    /**
     * 批量删除
     *
     * @param ids
     *
     * @return
     */
    int remote(List<T> ids);

}
