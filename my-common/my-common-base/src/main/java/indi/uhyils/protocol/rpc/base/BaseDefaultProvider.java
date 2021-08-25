package indi.uhyils.protocol.rpc.base;

import indi.uhyils.pojo.DTO.BaseDbDTO;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.RemoveCommand;
import indi.uhyils.pojo.cqe.query.BaseQuery;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.service.BaseDoService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 17时57分
 */
public abstract class BaseDefaultProvider<T extends BaseDbDTO> implements DefaultDTOProvider<T> {

    @Override
    public ServiceResult<Page<T>> query(BaseQuery query) {
        Page<T> page = getService().query(query);
        return ServiceResult.buildSuccessResult(page);
    }

    /**
     * 根据id查询
     *
     * @param query id
     *
     * @return 单条
     */
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
    public ServiceResult<ArrayList<T>> queryByIds(IdsQuery query) {
        List<T> dtos = getService().query(query);
        return ServiceResult.buildSuccessResult(new ArrayList<>(dtos));
    }

    @Override
    public ServiceResult<Long> add(AddCommand addCommand) {
        Identifier add = getService().add(addCommand);
        return ServiceResult.buildSuccessResult(add.getId());
    }

    @Override
    public ServiceResult<Integer> change(ChangeCommand<T> changeCommand) {
        int updateCount = getService().update(changeCommand);
        return ServiceResult.buildSuccessResult(updateCount);
    }

    @Override
    public ServiceResult<Integer> remove(RemoveCommand removeCommand) {
        int remove = getService().remove(removeCommand);
        return ServiceResult.buildSuccessResult(remove);
    }

    @Override
    public ServiceResult<Integer> remove(IdQuery id) {
        int remove = getService().remove(new Identifier(id.getId()));
        return ServiceResult.buildSuccessResult(remove);
    }

    @Override
    public ServiceResult<Integer> count(BaseQuery order) {
        Integer count = getService().count(order);
        return ServiceResult.buildSuccessResult(count);
    }
//
//    @Override
//    public ServiceResult<Page<T>> getByArgs(ArgsRequest argsRequest) {
//        getService().query()
//        List<Arg> args = argsRequest.getArgs();
//        Boolean paging = argsRequest.getPaging();
//        ArrayList<T> byArgs;
//        if (Boolean.TRUE.equals(paging)) {
//            byArgs = getDao().getByArgs(args, argsRequest.getPage(), argsRequest.getSize());
//        } else {
//            byArgs = getDao().getByArgsNoPage(args);
//        }
//        int count = getDao().countByArgs(argsRequest.getArgs());
//        Page<T> build = Page.build(argsRequest, byArgs, count, null);
//        return ServiceResult.buildSuccessResult("查询成功", build);
//    }
//
//    @Override
//    public ServiceResult<T> getById(IdRequest idRequest) {
//        T byId = getDao().getById(idRequest.getId());
//        if (byId == null) {
//            return ServiceResult.buildFailedResult("查询失败", null);
//        }
//        return ServiceResult.buildSuccessResult("查询成功", byId);
//    }
//
//    @Override
//    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
//    public ServiceResult<Integer> insert(ObjRequest<T> insert) throws Exception {
//        T data = insert.getData();
//        data.preInsert(insert);
//        int count = getDao().insert(data);
//        if (count == 1) {
//            return ServiceResult.buildSuccessResult("创建成功", count);
//        }
//        return ServiceResult.buildFailedResult("创建失败", 0);
//    }
//
//    @Override
//    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
//    public ServiceResult<Integer> update(ObjRequest<T> update) {
//        T data = update.getData();
//        data.preUpdate(update);
//        int count = getDao().update(data);
//        if (count != 0) {
//            return ServiceResult.buildSuccessResult("修改成功", count);
//        } else {
//            return ServiceResult.buildFailedResult("修改失败", 0);
//        }
//    }
//
//    @Override
//    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
//    public ServiceResult<Integer> delete(IdRequest idRequest) {
//        T byId = getDao().getById(idRequest.getId());
//        if (byId == null) {
//            return ServiceResult.buildFailedResult("查无此人", null);
//        }
//        byId.setDeleteFlag(true);
//        byId.preUpdate(idRequest);
//        int delete = getDao().update(byId);
//        if (delete != 0) {
//            return ServiceResult.buildSuccessResult("删除成功", delete);
//        } else {
//            return ServiceResult.buildFailedResult("删除失败", delete);
//        }
//    }
//
//    @Override
//    public ServiceResult<Integer> countByArgs(ArgsRequest argsRequest) {
//        List<Arg> args = argsRequest.getArgs();
//        int count = getDao().countByArgs(args);
//        return ServiceResult.buildSuccessResult("查询数量成功", count);
//    }


    /**
     * 获取service
     *
     * @return
     */
    protected abstract BaseDoService<T> getService();
}
