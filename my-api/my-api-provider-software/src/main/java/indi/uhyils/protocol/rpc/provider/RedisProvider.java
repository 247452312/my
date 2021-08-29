package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.RedisDO;
import indi.uhyils.pojo.DTO.request.GetRedisKeysRequest;
import indi.uhyils.pojo.DTO.request.RedisKeyAndValue;
import indi.uhyils.pojo.DTO.request.base.IdRequest;
import indi.uhyils.pojo.DTO.request.base.IdsRequest;
import indi.uhyils.pojo.DTO.request.base.ObjRequest;
import indi.uhyils.pojo.DTO.response.GetInfosResponse;
import indi.uhyils.pojo.DTO.response.OperateSoftwareResponse;
import indi.uhyils.pojo.DTO.response.RedisKeyResponse;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.ArrayList;

/**
 * redisService
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 12时56分
 */
public interface RedisProvider extends DTOProvider<RedisDO> {

    /**
     * 刷新某一个id的状态
     *
     * @param request
     *
     * @return
     */
    ServiceResult<RedisDO> reload(IdRequest request);


    /**
     * 启动redis
     *
     * @param request id
     *
     * @return 返回信息
     */
    ServiceResult<OperateSoftwareResponse> start(IdRequest request);


    /**
     * 停止某一个redis
     *
     * @param request id
     *
     * @return 返回信息
     */
    ServiceResult<OperateSoftwareResponse> stop(IdRequest request);


    /**
     * 批量删除redis
     *
     * @param request 多个redis id
     *
     * @return 删除是否成功
     */
    ServiceResult<Boolean> deleteManyRedis(IdsRequest request);


    /**
     * 批量更新redis信息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> reloadManyRedis(IdsRequest request);

    /**
     * 开启选中的redis
     *
     * @param request 多个redis id
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> startManyRedis(IdsRequest request);

    /**
     * 停止选中的redis
     *
     * @param request 多个redis id
     *
     * @return 是否成功
     */
    ServiceResult<Boolean> stopManyRedis(IdsRequest request);


    /**
     * redis的key值
     *
     * @param request 仓库名称
     *
     * @return redis的key
     */
    ServiceResult<ArrayList<RedisKeyResponse>> getRedisKeys(GetRedisKeysRequest request);

    /**
     * 获取redis仓库名称
     *
     * @param request 请求
     *
     * @return 仓库名称
     */
    ServiceResult<Integer> getRedisDb(IdRequest request);

    /**
     * 添加新的key 如果成功返回1 如果有重复 返回2
     *
     * @param request redis中的实体
     *
     * @return 如果成功 返回1 如果有重复 返回2
     */
    ServiceResult<Integer> addKey(ObjRequest<RedisKeyAndValue> request);

    /**
     * 添加新的key 如果成功返回1
     * 如果有重复就覆盖 啥也不管
     *
     * @param request redis中的实体
     *
     * @return 如果成功 返回1 如果有重复 返回2
     */
    ServiceResult<Integer> addKeyCover(ObjRequest<RedisKeyAndValue> request);

    /**
     * 修改key值
     *
     * @param request 请求
     *
     * @return 1->成功 2->没有此key
     */
    ServiceResult<Integer> updateKey(ObjRequest<RedisKeyAndValue> request);


    /**
     * 获取key的value值
     *
     * @param request redisId,DB,key
     *
     * @return
     */
    ServiceResult<String> getValueByKey(ObjRequest<RedisKeyAndValue> request);


    /**
     * 删除指定的key
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> deleteRedisByKey(ObjRequest<RedisKeyAndValue> request);


    /**
     * 获取redis的内容
     *
     * @param request id
     *
     * @return jedis.info()
     */
    ServiceResult<ArrayList<GetInfosResponse>> getInfos(IdRequest request);


}
