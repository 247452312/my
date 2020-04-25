package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.model.ClassEntity;
import indi.uhyils.dao.ClassDao;
import indi.uhyils.request.*;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * class类的增删改查
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 12时35分
 */
@Service
public class ClassServiceImpl implements ClassService {
    private static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

    /**
     * 插入成功标志
     */
    private static final int INSERT_SUCCESS = 1;


    @Autowired
    private ClassDao classDao;

    @Override
    public ServiceResult<Page<ClassEntity>> getByArgs(ArgsRequest argsRequest) {
        ArrayList<ClassEntity> byArgs;
        if (argsRequest.getPaging() == true) {
            byArgs = classDao.getByArgsNoPage(argsRequest.getArgs());
        } else {
            byArgs = classDao.getByArgs(argsRequest.getArgs(), argsRequest.getPage(), argsRequest.getSize());
        }

        return ServiceResult.buildSuccessResult("查询成功", byArgs, argsRequest);
    }

    @Override
    public ServiceResult<ClassEntity> getById(IdRequest idRequest) {
        List<ClassEntity> byId = classDao.getById(idRequest.getId());
        if (byId == null && byId.size() == 0) {
            return ServiceResult.buildFailedResult("查询失败", null, idRequest);
        } else {
            return ServiceResult.buildSuccessResult("查询成功", byId.get(0), idRequest);
        }
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<ClassEntity> insert) {
        ClassEntity data = insert.getData();
        data.preInsert();
        int count = classDao.insert(data);
        if (count == INSERT_SUCCESS) {
            return ServiceResult.buildSuccessResult("插入成功", count, insert);
        } else {
            return ServiceResult.buildFailedResult("插入失败", count, insert);
        }
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<ClassEntity> update) {
        ClassEntity data = update.getData();
        data.preUpdate();
        int count = classDao.update(data);
        if (count != 0) {
            return ServiceResult.buildSuccessResult("修改成功", count, update);
        } else {
            return ServiceResult.buildFailedResult("修改失败", count, update);
        }
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        int count = classDao.delete(idRequest.getId());
        if (count != 0) {
            return ServiceResult.buildSuccessResult("删除成功", count, idRequest);
        } else {
            return ServiceResult.buildFailedResult("删除失败", count, idRequest);
        }
    }


    public ClassDao getClassDao() {
        return classDao;
    }

    public void setClassDao(ClassDao classDao) {
        this.classDao = classDao;
    }
}
