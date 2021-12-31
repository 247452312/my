package indi.uhyils.protocol.rpc.base;

import indi.uhyils.pojo.DTO.base.IdDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.RemoveCommand;
import indi.uhyils.pojo.cqe.command.base.AddCommand;
import indi.uhyils.pojo.cqe.query.BlackQuery;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.pojo.cqe.query.base.BaseArgQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.service.BaseDoService;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 17时57分
 */
public abstract class BaseDefaultProvider<T extends IdDTO> implements DTOProvider<T> {

    @Override
    public ServiceResult<Page<T>> query(BlackQuery query) {
        Page<T> result = getService().query(query.getArgs(), query.getOrder(), query.getLimit());
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<T> queryById(IdQuery query) {
        T result = getService().query(new Identifier(query.getId()));
        return ServiceResult.buildSuccessResult(result);
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
        List<Long> ids = query.getIds();
        List<T> result = getService().query(ids.stream().map(Identifier::new).collect(Collectors.toList()));
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Long> add(AddCommand<T> addCommand) {
        Long result = getService().add(addCommand.getDto());
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Integer> change(ChangeCommand<T> changeCommand) {
        Integer result = getService().update(changeCommand.getDto(), changeCommand.getOrder().getArgs());
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Integer> remove(RemoveCommand removeCommand) {
        BaseArgQuery order = removeCommand.getOrder();
        Integer result = getService().remove(order.getArgs());
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Integer> remove(IdCommand id) {
        Integer result = getService().remove(new Identifier(id.getId()));
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Long> count(BlackQuery order) {
        Long result = getService().count(order.getArgs());
        return ServiceResult.buildSuccessResult(result);
    }

    /**
     * 获取service
     *
     * @return
     */
    protected abstract BaseDoService<T> getService();
}
