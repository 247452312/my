package indi.uhyils.repository.base;

import com.baomidou.mybatisplus.extension.service.IService;
import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.base.IdEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import java.util.List;

/**
 * 基础仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 14时49分
 */
public interface BaseEntityRepository<DO extends BaseDO, EN extends AbstractDoEntity<DO>> extends BaseRepository, IService<DO> {

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
    <E extends IdEntity> EN find(E id);

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
     * @param args  条件
     * @param order 排序
     *
     * @return
     */
    List<EN> findNoPage(List<Arg> args, Order order);

    /**
     * 根据条件查询
     *
     * @param args 条件
     *
     * @return
     */
    List<EN> findNoPage(List<Arg> args);

    /**
     * 根据条件分页查询
     *
     * @param args
     * @param order
     * @param limit
     *
     * @return
     */
    Page<EN> find(List<Arg> args, Order order, Limit limit);


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
     * @param ids
     *
     * @return
     */
    <E extends Identifier> int removeByIds(List<E> ids);

    /**
     * 批量删除
     *
     * @param args  条件
     * @param limit 删除n个
     *
     * @return
     */
    int remove(List<Arg> args, Limit limit);

    /**
     * 修改
     *
     * @param entity
     * @param args
     *
     * @return
     */
    int change(EN entity, List<Arg> args);

    /**
     * 数量
     *
     * @param args
     *
     * @return
     */
    long count(List<Arg> args);
}
