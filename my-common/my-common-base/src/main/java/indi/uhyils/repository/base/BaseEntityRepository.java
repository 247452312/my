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
public interface BaseEntityRepository<EN extends BaseEntity> extends BaseRepository {

    /**
     * 保存逻辑
     *
     * @param entity
     *
     * @return id
     */
    Identifier save(EN entity);

    /**
     * 批量报错或修改
     *
     * @param entities
     *
     * @return
     */
    List<Identifier> save(List<EN> entities);

    /**
     * 根据id查询
     *
     * @param id 主键id
     *
     * @return
     */
    <E extends HaveIdEntity> EN find(E id);

    /**
     * 根据id查询
     *
     * @param ids 主键ids
     *
     * @return
     */
    <E extends Identifier> List<EN> find(List<E> ids);

    /**
     * 根据id查询
     *
     * @param id 主键id
     *
     * @return
     */
    <E extends Identifier> EN find(E id);

    /**
     * 根据条件查询
     *
     * @param order 主键id
     *
     * @return
     */
    <E extends BaseQuery> List<EN> findNoPage(E order);

    /**
     * 根据条件分页查询
     *
     * @param order 主键id
     *
     * @return
     */
    <E extends BaseQuery> Page<EN> find(E order);


    /**
     * 批量删除
     *
     * @param entitys
     *
     * @return
     */
    int remove(List<EN> entitys);

    /**
     * 批量删除
     *
     * @param ids
     *
     * @return
     */
    <E extends Identifier> int remove(E... ids);

    /**
     * 批量删除
     *
     * @param order
     *
     * @return
     */
    <E extends BaseQuery> int remove(E order);

    /**
     * 修改
     *
     * @return
     */
    <E extends BaseQuery> int change(EN entity, E query);

    /**
     * 数量
     *
     * @param order
     *
     * @return
     */
    <E extends BaseQuery> int count(E order);
}
