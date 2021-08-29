package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PowerDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetMethodNameByInterfaceNameQuery;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.CheckUserHavePowerQuery;
import indi.uhyils.protocol.rpc.PowerProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PowerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
public class PowerProviderImpl extends BaseDefaultProvider<PowerDTO> implements PowerProvider {


    @Autowired
    private PowerService service;

    @Override
    public ServiceResult<List<PowerDTO>> getPowers(DefaultCQE request) {
        List<PowerDTO> result = service.getPowers(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Boolean> checkUserHavePower(CheckUserHavePowerQuery request) {
        Boolean result = service.checkUserHavePower(request);
        return ServiceResult.buildSuccessResult(result);


    }

    @Override
    public ServiceResult<Boolean> deletePower(IdCommand request) {
        Boolean result = service.deletePower(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<String>> getInterfaces(DefaultCQE request) {
        List<String> result = service.getInterfaces(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<String>> getMethodNameByInterfaceName(GetMethodNameByInterfaceNameQuery request) {
        List<String> result = service.getMethodNameByInterfaceName(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Integer> initPowerInProStart(DefaultCQE request) throws Exception {
        Integer result = service.initPowerInProStart(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    protected BaseDoService<PowerDTO> getService() {
        return service;
    }
}
