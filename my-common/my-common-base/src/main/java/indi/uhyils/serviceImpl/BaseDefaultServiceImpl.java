package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.model.base.BaseDoEntity;
import indi.uhyils.pojo.request.base.ArgsRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 17时57分
 */
public abstract class BaseDefaultServiceImpl<T extends BaseDoEntity> implements DefaultEntityService<T> {

    @Override
    public ServiceResult<Page<T>> getByArgs(ArgsRequest argsRequest) {
        List<Arg> args = argsRequest.getArgs();
        Boolean paging = argsRequest.getPaging();
        ArrayList<T> byArgs;
        if (paging) {
            byArgs = getDao().getByArgs(args, argsRequest.getPage(), argsRequest.getSize());
        } else {
            byArgs = getDao().getByArgsNoPage(args);
        }
        int count = getDao().countByArgs(argsRequest.getArgs());
        Page<T> build = Page.build(argsRequest, byArgs, count, null);
        return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
    }

    @Override
    public ServiceResult<T> getById(IdRequest idRequest) {
        T byId = getDao().getById(idRequest.getId());
        if (byId == null) {
            return ServiceResult.buildFailedResult("查询失败", null, idRequest);
        }
        return ServiceResult.buildSuccessResult("查询成功", byId, idRequest);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> insert(ObjRequest<T> insert) throws Exception {
        T data = insert.getData();
        data.preInsert(insert);
        int count = getDao().insert(data);
        if (count == 1) {
            return ServiceResult.buildSuccessResult("创建成功", count, insert);
        }
        return ServiceResult.buildFailedResult("创建失败", 0, insert);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> update(ObjRequest<T> update) {
        T data = update.getData();
        data.preUpdate(update);
        int count = getDao().update(data);
        if (count != 0) {
            return ServiceResult.buildSuccessResult("修改成功", count, update);
        } else {
            return ServiceResult.buildFailedResult("修改失败", 0, update);
        }
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        T byId = getDao().getById(idRequest.getId());
        if (byId == null) {
            return ServiceResult.buildFailedResult("查无此人", null, idRequest);
        }
        byId.setDeleteFlag(true);
        byId.preUpdate(idRequest);
        int delete = getDao().update(byId);
        if (delete != 0) {
            return ServiceResult.buildSuccessResult("删除成功", delete, idRequest);
        } else {
            return ServiceResult.buildFailedResult("删除失败", delete, idRequest);
        }
    }

    @Override
    public ServiceResult<Integer> countByArgs(ArgsRequest argsRequest) {
        List<Arg> args = argsRequest.getArgs();
        int count = getDao().countByArgs(args);
        return ServiceResult.buildSuccessResult("查询数量成功", count, argsRequest);
    }


    /**
     * 获取dao
     *
     * @return
     */
    protected abstract DefaultDao<T> getDao();
}
