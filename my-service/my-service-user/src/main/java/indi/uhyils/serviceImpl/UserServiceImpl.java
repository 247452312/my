package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.dao.UserDao;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.model.UserEntity;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.UserService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时51分
 */
@Service
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


    @Override
    public ServiceResult<Page<UserEntity>> getByClassId(IdRequest idRequest) {
        ArrayList<UserEntity> byClassId = userDao.getByClassId(idRequest.getId());
        return ServiceResult.buildSuccessResult("查询成功", byClassId, idRequest);
    }

    @Override
    public ServiceResult<Page<UserEntity>> getByArgs(ArgsRequest argsRequest) {
        if (argsRequest.getPaging() == true) {
            ArrayList<UserEntity> byArgs = userDao.getByArgs(argsRequest.getArgs(), argsRequest.getPage(), argsRequest.getSize());
            return ServiceResult.buildSuccessResult("查询成功", byArgs, argsRequest);
        } else {
            ArrayList<UserEntity> byArgs = userDao.getByArgsNoPage(argsRequest.getArgs());
            return ServiceResult.buildSuccessResult("查询成功", byArgs, argsRequest);
        }
    }

    @Override
    public ServiceResult<UserEntity> getById(IdRequest idRequest) {
        List<UserEntity> byId = userDao.getById(idRequest.getId());
        if (byId != null && byId.size() == 1) {
            return ServiceResult.buildSuccessResult("查询成功", byId.get(0), idRequest);
        } else {
            return ServiceResult.buildFailedResult("查询失败", null, idRequest);
        }
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<UserEntity> insert) {
        UserEntity data = insert.getData();
        data.preInsert();
        int insert1 = userDao.insert(data);
        return ServiceResult.buildSuccessResult("插入成功", insert1, insert);
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<UserEntity> update) {
        UserEntity data = update.getData();
        data.preUpdate();
        int update1 = userDao.update(data);
        return ServiceResult.buildSuccessResult("修改成功", update1, update);
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        int delete1 = userDao.delete(idRequest.getId());
        return ServiceResult.buildSuccessResult("删除成功", delete1, idRequest);
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
