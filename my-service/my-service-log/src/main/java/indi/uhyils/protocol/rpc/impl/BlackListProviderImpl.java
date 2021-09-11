package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.BlackListDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.AddBlackIpRequest;
import indi.uhyils.pojo.DTO.request.GetLogIntervalByIpQuery;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.BlackListProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.BlackListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 黑名单(BlackList)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分52秒
 */
@RpcService
public class BlackListProviderImpl extends BaseDefaultProvider<BlackListDTO> implements BlackListProvider {


    @Autowired
    private BlackListService service;

    @Override
    protected BaseDoService<BlackListDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<Boolean> getLogIntervalByIp(GetLogIntervalByIpQuery request) {
        Boolean result = service.getLogIntervalByIp(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<String>> getAllIpBlackList(DefaultCQE request) {
        List<String> result = service.getAllIpBlackList(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> addBlackIp(AddBlackIpRequest request) {
        Boolean result = service.addBlackIp(request);
        return ServiceResult.buildSuccessResult(result);
    }
}

