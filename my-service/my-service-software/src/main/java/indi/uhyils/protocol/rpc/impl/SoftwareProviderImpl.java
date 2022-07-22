package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.SoftwareDTO;
import indi.uhyils.pojo.DTO.request.GetRedisKeysQuery;
import indi.uhyils.pojo.DTO.request.RedisKeyAndValue;
import indi.uhyils.pojo.DTO.response.GetInfosResponse;
import indi.uhyils.pojo.DTO.response.OperateSoftwareResponse;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.pojo.cqe.command.base.AddCommand;
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
    public SoftwareDTO reload(IdCommand request) {
        return service.reload(request);
    }

    @Override
    public OperateSoftwareResponse start(IdCommand request) {
        return service.start(request);
    }

    @Override
    public OperateSoftwareResponse stop(IdCommand request) {
        return service.stop(request);
    }

    @Override
    public Boolean deleteManyRedis(IdsCommand request) {
        return service.deleteManyRedis(request);
    }

    @Override
    public Boolean reloadManyRedis(IdsCommand request) {
        return service.reloadManyRedis(request);
    }

    @Override
    public Boolean startManyRedis(IdsCommand request) {
        return service.startManyRedis(request);
    }

    @Override
    public Boolean stopManyRedis(IdsCommand request) {
        return service.stopManyRedis(request);
    }

    @Override
    public List<String> getRedisKeys(GetRedisKeysQuery request) {
        return service.getRedisKeys(request);
    }

    @Override
    public Integer getRedisDb(IdQuery request) {
        return service.getRedisDb(request);
    }

    @Override
    public Integer addKey(AddCommand<RedisKeyAndValue> request) {
        return service.addKey(request);
    }

    @Override
    public Integer addKeyCover(AddCommand<RedisKeyAndValue> request) {
        return service.addKeyCover(request);
    }

    @Override
    public Integer updateKey(ChangeCommand<RedisKeyAndValue> request) {
        return service.updateKey(request);
    }

    @Override
    public String getValueByKey(KeyQuery request) {
        return service.getValueByKey(request);
    }

    @Override
    public Boolean deleteRedisByKey(ChangeCommand<RedisKeyAndValue> request) {
        return service.deleteRedisByKey(request);
    }

    @Override
    public List<GetInfosResponse> getInfos(IdQuery request) {
        return service.getInfos(request);
    }
}

