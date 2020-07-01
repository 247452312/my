package indi.uhyils.serviceImpl;

import indi.uhyils.model.MongoEntity;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.request.base.ObjsRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.MongoService;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 07时08分
 */
public class MongoServiceImpl implements MongoService {

    @Override
    public ServiceResult<Boolean> add(ObjsRequest<MongoEntity> request) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> delete(IdRequest idRequest) {
        return null;
    }

    @Override
    public ServiceResult<Boolean> update(ObjsRequest<MongoEntity> request) {
        return null;
    }

    @Override
    public ServiceResult<MongoEntity> getByKey(IdRequest idRequest) {
        return null;
    }

    @Override
    public ServiceResult<ArrayList<MongoEntity>> getByKeyStart(IdRequest idRequest) {
        return null;
    }
}
