package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.model.UserEntity;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.request.model.Arg;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.GoodService;
import indi.uhyils.model.GoodEntity;
import indi.uhyils.dao.GoodDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月03日 17时06分
 */
@Service
public class GoodServiceImpl implements GoodService {


    private static final Logger logger = LoggerFactory.getLogger(GoodServiceImpl.class);

    @Autowired
    private GoodDao goodDao;

    @Override
    public ServiceResult<Page<GoodEntity>> getByArgs(ArgsRequest argsRequest) {
        List<Arg> args = argsRequest.getArgs();
        Boolean paging = argsRequest.getPaging();
        if (paging == true) { //分页
            ArrayList<GoodEntity> byArgs = goodDao.getByArgs(args, argsRequest.getPage(), argsRequest.getSize());
            int count = goodDao.count();
            Page<UserEntity> build = Page.build(argsRequest, byArgs, count, (count / argsRequest.getSize()) + 1);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        } else {
            ArrayList<GoodEntity> byArgs = goodDao.getByArgsNoPage(args);
            int count = goodDao.count();
            Page<UserEntity> build = Page.build(argsRequest, byArgs, count, null);
            return ServiceResult.buildSuccessResult("查询成功", build, argsRequest);
        }
    }

    @Override
    public ServiceResult<GoodEntity> getById(IdRequest idRequest) {
        List<GoodEntity> byId = goodDao.getById(idRequest.getId());
        if (byId != null && byId.size() == 1) {
            return ServiceResult.buildSuccessResult("查询成功", byId.get(0), idRequest);
        }
        return ServiceResult.buildFailedResult("没查到", null, idRequest);
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<GoodEntity> insert) {
        GoodEntity data = insert.getData();
        data.preInsert(insert);
        int count = goodDao.insert(data);
        if (count == 1) {
            return ServiceResult.buildSuccessResult("创建成功", count, insert);
        }
        return ServiceResult.buildFailedResult("创建失败", 0, insert);
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<GoodEntity> update) {
        GoodEntity data = update.getData();
        data.preUpdate(update);
        int count = goodDao.update(data);
        if (count != 0) {
            return ServiceResult.buildSuccessResult("修改成功", count, update);
        } else {
            return ServiceResult.buildFailedResult("修改失败", 0, update);
        }
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        int delete = goodDao.delete(idRequest.getId());
        if (delete != 0) {
            return ServiceResult.buildSuccessResult("删除成功", delete, idRequest);
        } else {
            return ServiceResult.buildFailedResult("删除失败", delete, idRequest);
        }
    }
}
