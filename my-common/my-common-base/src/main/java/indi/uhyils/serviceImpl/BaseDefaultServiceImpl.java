package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DefaultDao;
import indi.uhyils.pojo.model.base.BaseEntity;
import indi.uhyils.pojo.request.ArgsRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.Page;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.DefaultEntityService;
import indi.uhyils.util.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 17时57分
 */
public abstract class BaseDefaultServiceImpl<T extends BaseEntity> implements DefaultEntityService<T> {

    @Override
    public ServiceResult<Page<T>> getByArgs(ArgsRequest argsRequest) {
        List<Arg> args = argsRequest.getArgs();
        Boolean paging = argsRequest.getPaging();
        if (paging) {
            ArrayList<T> byArgs = getDao().getByArgs(args, argsRequest.getPage(), argsRequest.getSize());
            int count = getDao().countByArgs(argsRequest.getArgs());
            Page<T> build = Page.build(argsRequest, byArgs, count, (count / argsRequest.getSize()) + 1);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        } else {
            ArrayList<T> byArgs = getDao().getByArgsNoPage(args);
            int count = getDao().countByArgs(argsRequest.getArgs());
            Page<T> build = Page.build(argsRequest, byArgs, count, null);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        }
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
    public ServiceResult<Integer> insert(ObjRequest<T> insert) {
        T data = insert.getData();
        data.preInsert(insert);
        int count = getDao().insert(data);
        if (count == 1) {
            return ServiceResult.buildSuccessResult("创建成功", count, insert);
        }
        return ServiceResult.buildFailedResult("创建失败", 0, insert);
    }

    @Override
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


    private DefaultDao<T> getDao() {
        try {
            Method declaredMethod = getClass().getDeclaredMethod("getDao");
            return (DefaultDao<T>) declaredMethod.invoke(this);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.error(this,e);
        }
        return null;
    }
}
