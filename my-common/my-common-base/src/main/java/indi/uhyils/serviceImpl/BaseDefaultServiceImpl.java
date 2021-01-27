package indi.uhyils.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.enum_.SqlSymbolEnum;
import indi.uhyils.pojo.model.base.BaseVoEntity;
import indi.uhyils.pojo.request.base.ArgsRequest;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 17时57分
 */
public abstract class BaseDefaultServiceImpl<T extends BaseVoEntity> implements DefaultEntityService<T> {

    @Override
    public ServiceResult<Page<T>> getByArgs(ArgsRequest argsRequest) {
        Boolean paging = argsRequest.getPaging();
        Page<T> build;
        QueryWrapper<T> queryWrapper = getQueryWrapper(argsRequest.getArgs());
        if (paging) {
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> tPage = getDao().selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(argsRequest.getPage(), argsRequest.getSize()), queryWrapper);
            build = Page.build(argsRequest, tPage.getRecords(), tPage.getTotal(), tPage.getTotal() / tPage.getSize());
        } else {

            List<T> ts = getDao().selectList(queryWrapper);
            Integer integer = getDao().selectCount(queryWrapper);
            build = Page.build(argsRequest, ts, integer.longValue(), null);
        }

        return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
    }

    protected <E extends BaseVoEntity> QueryWrapper<E> getQueryWrapper(List<Arg> args,Class<E> clazz) {
        QueryWrapper<E> queryWrapper = new QueryWrapper<>();
        for (Arg arg : args) {
            String symbol = arg.getSymbol();
            SqlSymbolEnum parse = SqlSymbolEnum.parse(symbol);
            parse.excute(queryWrapper, arg.getName(), arg.getData());
        }
        return queryWrapper;
    }
    protected  QueryWrapper<T> getQueryWrapper(List<Arg> args) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        for (Arg arg : args) {
            String symbol = arg.getSymbol();
            SqlSymbolEnum parse = SqlSymbolEnum.parse(symbol);
            parse.excute(queryWrapper, arg.getName(), arg.getData());
        }
        return queryWrapper;
    }

    @Override
    public ServiceResult<T> getById(IdRequest idRequest) {
        T byId = getDao().selectById(idRequest.getId());
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
        int count = getDao().updateById(data);
        if (count != 0) {
            return ServiceResult.buildSuccessResult("修改成功", count, update);
        } else {
            return ServiceResult.buildFailedResult("修改失败", 0, update);
        }
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        T byId = getDao().selectById(idRequest.getId());
        if (byId == null) {
            return ServiceResult.buildFailedResult("查无此人", null, idRequest);
        }
        byId.setDeleteFlag(true);
        byId.preUpdate(idRequest);
        int delete = getDao().updateById(byId);
        if (delete != 0) {
            return ServiceResult.buildSuccessResult("删除成功", delete, idRequest);
        } else {
            return ServiceResult.buildFailedResult("删除失败", delete, idRequest);
        }
    }

    @Override
    public ServiceResult<Integer> countByArgs(ArgsRequest argsRequest) {
        List<Arg> args = argsRequest.getArgs();
        int count = getDao().selectCount(getQueryWrapper(args));
        return ServiceResult.buildSuccessResult("查询数量成功", count, argsRequest);
    }


    /**
     * 获取dao
     *
     * @return
     */
    protected abstract DefaultDao<T> getDao();
}
