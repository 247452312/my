package indi.uhyils.protocol.rpc.base;

import indi.uhyils.pojo.DTO.BaseDbDTO;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.RemoveCommand;
import indi.uhyils.pojo.cqe.query.BaseQuery;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.service.BaseDoService;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 17时57分
 */
public abstract class BaseDefaultProvider<T extends BaseDbDTO> implements DTOProvider<T> {

    @Override
    public ServiceResult<Page<T>> query(BaseQuery query) {
        return getService().query(query);
    }

    @Override
    public ServiceResult<T> queryById(IdQuery query) {
        return getService().query(new Identifier(query.getId()));
    }

    /**
     * 根据id查询
     *
     * @param query id
     *
     * @return 单条
     */
    @Override
    public ServiceResult<List<T>> queryByIds(IdsQuery query) {
        return getService().query(query);
    }

    @Override
    public ServiceResult<Long> add(AddCommand<T> addCommand) {
        return getService().add(addCommand);
    }

    @Override
    public ServiceResult<Integer> change(ChangeCommand<T> changeCommand) {
        return getService().update(changeCommand);
    }

    @Override
    public ServiceResult<Integer> remove(RemoveCommand removeCommand) {
        return getService().remove(removeCommand);
    }

    @Override
    public ServiceResult<Integer> remove(IdCommand id) {
        return getService().remove(id);
    }

    @Override
    public ServiceResult<Integer> count(BaseQuery order) {
        return getService().count(order);
    }

    /**
     * 获取service
     *
     * @return
     */
    protected abstract BaseDoService<T> getService();
}
