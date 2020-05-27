package indi.uhyils.serviceImpl;

import indi.uhyils.dao.DefaultDao;
import indi.uhyils.pojo.model.DataEntity;
import indi.uhyils.pojo.request.ArgsRequest;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.request.ObjRequest;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.Page;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.service.DefaultEntityService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 17时57分
 */
public abstract class DefaultServiceImpl<T extends DataEntity> implements DefaultEntityService<T> {

    @Override
    public ServiceResult<Page<T>> getByArgs(ArgsRequest argsRequest) {
        List<Arg> args = argsRequest.getArgs();
        Boolean paging = argsRequest.getPaging();
        if (paging == true) {
            ArrayList<T> byArgs = getDao().getByArgs(args, argsRequest.getPage(), argsRequest.getSize());
            int count = getDao().count();
            Page<T> build = Page.build(argsRequest, byArgs, count, (count / argsRequest.getSize()) + 1);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        } else {
            ArrayList<T> byArgs = getDao().getByArgsNoPage(args);
            int count = getDao().count();
            Page<T> build = Page.build(argsRequest, byArgs, count, null);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        }
    }

    @Override
    public ServiceResult<T> getById(IdRequest idRequest) {
        List<T> byId = getDao().getById(idRequest.getId());
        if (byId != null && byId.size() == 1) {
            return ServiceResult.buildSuccessResult("查询成功", byId.get(0), idRequest);
        }
        return ServiceResult.buildFailedResult("查无此人", null, idRequest);
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
        List<T> byId = getDao().getById(idRequest.getId());
        if (byId == null) {
            return ServiceResult.buildFailedResult("查无此人", null, idRequest);
        }
        T t = byId.get(0);
        t.setDeleteFlag(true);
        t.preUpdate(idRequest);
        int delete = getDao().update(t);
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


    private DefaultDao getDao() {
        try {
            Method declaredMethod = getClass().getDeclaredMethod("getDao");
            return (DefaultDao) declaredMethod.invoke(this, null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
