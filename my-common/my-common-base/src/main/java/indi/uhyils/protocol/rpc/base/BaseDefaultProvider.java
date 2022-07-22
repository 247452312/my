package indi.uhyils.protocol.rpc.base;

import indi.uhyils.pojo.DTO.base.IdDTO;
import indi.uhyils.pojo.DTO.base.Page;
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
import indi.uhyils.util.LogUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 17时57分
 */
public abstract class BaseDefaultProvider<T extends IdDTO> implements DTOProvider<T> {

    @Override
    public Page<T> query(BlackQuery query) {
        return getService().query(query.getArgs(), query.getOrder(), query.getLimit());
    }

    @Override
    public List<T> queryNoPage(BlackQuery query) {
        return getService().queryNoPage(query.getArgs(), query.getOrder());
    }

    @Override
    public T queryById(IdQuery query) {
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
    public List<T> queryByIds(IdsQuery query) {
        List<Long> ids = query.getIds();
        List<T> result = null;
        try {
            result = getService().query(ids.stream().map(Identifier::new).collect(Collectors.toList()));
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
        return result;
    }

    @Override
    public Long add(AddCommand<T> addCommand) {
        return getService().add(addCommand.getDto());
    }

    @Override
    public Integer change(ChangeCommand<T> changeCommand) {
        return getService().update(changeCommand.getDto(), changeCommand.getOrder().getArgs());
    }

    @Override
    public Integer remove(RemoveCommand removeCommand) {
        BaseArgQuery order = removeCommand.getOrder();
        return getService().remove(order.getArgs());
    }

    @Override
    public Integer remove(IdCommand id) {
        return getService().remove(new Identifier(id.getId()));
    }

    @Override
    public Long count(BlackQuery order) {
        return getService().count(order.getArgs());
    }

    /**
     * 获取service
     *
     * @return
     */
    protected abstract BaseDoService<T> getService();
}
