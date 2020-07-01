package indi.uhyils.service;

import indi.uhyils.model.MongoEntity;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.request.base.ObjsRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.BaseService;

import java.util.ArrayList;

/**
 * mongo接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 06时35分
 */
public interface MongoService extends BaseService {

    /**
     * 添加
     *
     * @param request mongo实体
     * @return 是否成功
     */
    ServiceResult<Boolean> add(ObjsRequest<MongoEntity> request);

    /**
     * 删除
     *
     * @param idRequest key
     * @return 是否成功
     */
    ServiceResult<Boolean> delete(IdRequest idRequest);

    /**
     * 改
     *
     * @param request mongo实体
     * @return 是否成功
     */
    ServiceResult<Boolean> update(ObjsRequest<MongoEntity> request);

    /**
     * 根据key查询
     *
     * @param idRequest key
     * @return mongo实体
     */
    ServiceResult<MongoEntity> getByKey(IdRequest idRequest);

    /**
     * 查询key以传入值为开头的所有实体
     *
     * @param idRequest key
     * @return key以传入值为开头的所有实体
     */
    ServiceResult<ArrayList<MongoEntity>> getByKeyStart(IdRequest idRequest);


}
