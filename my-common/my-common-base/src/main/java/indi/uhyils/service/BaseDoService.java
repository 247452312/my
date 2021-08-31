package indi.uhyils.service;

import indi.uhyils.pojo.DTO.IdDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.RemoveCommand;
import indi.uhyils.pojo.cqe.query.BaseArgQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import java.util.List;

/**
 * 代表有数据库对象映射的service,带有一些默认方法
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时35分
 */
public interface BaseDoService<E extends IdDTO> extends BaseService {

    /**
     * 插入
     *
     * @param addCommand
     *
     * @return
     */
    Long add(AddCommand<E> addCommand);

    /**
     * 根据id 删除
     *
     * @param id
     *
     * @return
     */
    Integer remove(IdCommand id);

    /**
     * 根据条件删除
     *
     * @param removeCommand
     *
     * @return
     */
    Integer remove(RemoveCommand removeCommand);

    /**
     * 查询
     *
     * @param order
     */
    Page<E> query(BaseArgQuery order);

    /**
     * 查询
     *
     * @param order
     */
    List<E> query(IdsQuery order);

    /**
     * 查询
     *
     * @param order
     */
    List<E> queryNoPage(BaseArgQuery order);

    /**
     * 根据id获取DTO
     *
     * @param id id
     *
     * @return
     */
    E query(Identifier id);

    /**
     * 修改
     *
     * @param changeCommand
     *
     * @return
     */
    Integer update(ChangeCommand<E> changeCommand);


    /**
     * 数量
     *
     * @param order
     *
     * @return
     */
    Integer count(BaseArgQuery order);
}
