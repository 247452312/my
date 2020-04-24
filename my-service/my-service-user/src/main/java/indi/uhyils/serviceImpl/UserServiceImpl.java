package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.request.ArgsRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.request.ObjRequest;
import indi.uhyils.request.UserEntity;
import indi.uhyils.response.Page;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.UserService;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时51分
 */
@Service
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public ServiceResult<Page<UserEntity>> getByClassId(IdRequest idRequest) {
        return null;
    }

    @Override
    public ServiceResult<Page<UserEntity>> getByArgs(ArgsRequest argsRequest) {
        return null;
    }

    @Override
    public ServiceResult<UserEntity> getById(IdRequest idRequest) {
        return null;
    }

    @Override
    public ServiceResult<Integer> insert(ObjRequest<UserEntity> insert) {
        return null;
    }

    @Override
    public ServiceResult<Integer> update(ObjRequest<UserEntity> update) {
        return null;
    }

    @Override
    public ServiceResult<Integer> delete(IdRequest idRequest) {
        return null;
    }
}
