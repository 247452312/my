package indi.uhyils.repository.base;

import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.cqe.query.BaseQuery;
import indi.uhyils.pojo.entity.BaseEntity;
import indi.uhyils.pojo.entity.HaveIdEntity;
import indi.uhyils.pojo.entity.type.Identifier;
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
     * @param ids 主键ids
     *
     * @return
     */
    List<T> find(List<Identifier> ids);

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
    List<T> findNoPage(BaseQuery order);

    /**
     * 根据条件分页查询
     *
     * @param order 主键id
     *
     * @return
     */
    Page<T> find(BaseQuery order);


    /**
     * 批量删除
     *
     * @param ids
     *
     * @return
     */
    int remove(List<T> ids);

    /**
     * 批量删除
     *
     * @param ids
     *
     * @return
     */
    int remove(Identifier... ids);

    /**
     * 批量删除
     *
     * @param order
     *
     * @return
     */
    int remove(BaseQuery order);

    /**
     * 修改
     *
     * @return
     */
    int change(T entity, BaseQuery query);

    /**
     * 数量
     *
     * @param order
     *
     * @return
     */
    int count(BaseQuery order);
}
