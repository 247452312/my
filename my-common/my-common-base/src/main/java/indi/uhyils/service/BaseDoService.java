package indi.uhyils.service;

import indi.uhyils.pojo.DTO.BaseDbDTO;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.RemoveCommand;
import indi.uhyils.pojo.cqe.query.BaseQuery;
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
public interface BaseDoService<E extends BaseDbDTO> extends BaseService {

    /**
     * 插入
     *
     * @param addCommand
     *
     * @return
     */
    Identifier add(AddCommand addCommand);

    /**
     * 根据id 删除
     *
     * @param id
     *
     * @return
     */
    int remove(Identifier id);

    /**
     * 根据条件删除
     *
     * @param removeCommand
     *
     * @return
     */
    int remove(RemoveCommand removeCommand);

    /**
     * 查询
     *
     * @param order
     */
    Page<E> query(BaseQuery order);

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
    List<E> queryNoPage(BaseQuery order);

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
    int update(ChangeCommand<E> changeCommand);


    /**
     * 数量
     *
     * @param order
     *
     * @return
     */
    Integer count(BaseQuery order);
}
