package indi.uhyils.service;

import indi.uhyils.pojo.model.RedisEntity;
import indi.uhyils.pojo.request.IdRequest;
import indi.uhyils.pojo.response.ServiceResult;
import indi.uhyils.pojo.response.OperateSoftwareResponse;

/**
 * redisService
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 12时56分
 */
public interface RedisService extends DefaultEntityService<RedisEntity> {

    /**
     * 刷新某一个id的状态
     *
     * @param request
     * @return
     */
    ServiceResult<RedisEntity> reload(IdRequest request);


    /**
     * 启动redis
     *
     * @param request id
     * @return 返回信息
     */
    ServiceResult<OperateSoftwareResponse> start(IdRequest request);


    /**
     * 停止某一个redis
     *
     * @param request id
     * @return 返回信息
     */
    ServiceResult<OperateSoftwareResponse> stop(IdRequest request);

}
