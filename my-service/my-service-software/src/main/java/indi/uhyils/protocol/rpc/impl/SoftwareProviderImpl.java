package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.SoftwareDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetRedisKeysQuery;
import indi.uhyils.pojo.DTO.request.RedisKeyAndValue;
import indi.uhyils.pojo.DTO.response.GetInfosResponse;
import indi.uhyils.pojo.DTO.response.OperateSoftwareResponse;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.KeyQuery;
import indi.uhyils.protocol.rpc.SoftwareProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.SoftwareService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 中间件表(Software)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分22秒
 */
@RpcService
public class SoftwareProviderImpl extends BaseDefaultProvider<SoftwareDTO> implements SoftwareProvider {


    @Autowired
    private SoftwareService service;


    @Override
    protected BaseDoService<SoftwareDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<SoftwareDTO> reload(IdCommand request) {
        SoftwareDTO result = service.reload(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<OperateSoftwareResponse> start(IdCommand request) {
        OperateSoftwareResponse result = service.start(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<OperateSoftwareResponse> stop(IdCommand request) {
        OperateSoftwareResponse result = service.stop(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> deleteManyRedis(IdsCommand request) {
        Boolean result = service.deleteManyRedis(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> reloadManyRedis(IdsCommand request) {
        Boolean result = service.reloadManyRedis(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> startManyRedis(IdsCommand request) {
        Boolean result = service.startManyRedis(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> stopManyRedis(IdsCommand request) {
        Boolean result = service.stopManyRedis(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<String>> getRedisKeys(GetRedisKeysQuery request) {
        List<String> result = service.getRedisKeys(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Integer> getRedisDb(IdQuery request) {
        Integer result = service.getRedisDb(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Integer> addKey(AddCommand<RedisKeyAndValue> request) {
        Integer result = service.addKey(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Integer> addKeyCover(AddCommand<RedisKeyAndValue> request) {
        Integer result = service.addKeyCover(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Integer> updateKey(ChangeCommand<RedisKeyAndValue> request) {
        Integer result = service.updateKey(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<String> getValueByKey(KeyQuery request) {
        String result = service.getValueByKey(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> deleteRedisByKey(ChangeCommand<RedisKeyAndValue> request) {
        Boolean result = service.deleteRedisByKey(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<GetInfosResponse>> getInfos(IdQuery request) {
        List<GetInfosResponse> result = service.getInfos(request);
        return ServiceResult.buildSuccessResult(result);
    }
}

