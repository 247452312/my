package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.RelegationDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.RelegationCoverCommand;
import indi.uhyils.pojo.cqe.command.RelegationDemotionCommand;
import indi.uhyils.protocol.rpc.RelegationProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.RelegationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接口降级策略(Relegation)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时33分25秒
 */
@RpcService
public class RelegationProviderImpl extends BaseDefaultProvider<RelegationDTO> implements RelegationProvider {


    @Autowired
    private RelegationService service;


    @Override
    protected BaseDoService<RelegationDTO> getService() {
        return service;
    }


    @Override
    public ServiceResult<Boolean> demotion(RelegationDemotionCommand cqe) {
        Boolean result = service.demotion(cqe.getServiceName(), cqe.getMethodName());
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> recover(RelegationCoverCommand cqe) {
        Boolean result = service.recover(cqe.getServiceName(), cqe.getMethodName());
        return ServiceResult.buildSuccessResult(result);
    }
}

