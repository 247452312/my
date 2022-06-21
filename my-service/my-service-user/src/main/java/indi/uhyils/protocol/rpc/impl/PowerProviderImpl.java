package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.NoLogin;
import indi.uhyils.pojo.DTO.PowerDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetMethodNameByInterfaceNameQuery;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.CheckUserHavePowerQuery;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.type.InterfaceName;
import indi.uhyils.pojo.entity.type.MethodName;
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
        List<PowerDTO> result = service.getPowers();
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    @NoLogin
    public ServiceResult<Boolean> checkUserHavePower(CheckUserHavePowerQuery request) {
        InterfaceName interfaceName = new InterfaceName(request.getInterfaceName());
        MethodName methodName = new MethodName(request.getMethodName());
        Identifier userId = new Identifier(request.getUserId());
        Boolean result = service.checkUserHavePower(interfaceName, methodName, userId);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> deletePower(IdCommand request) {
        Identifier powerId = new Identifier(request.getId());
        Boolean result = service.deletePower(powerId);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<String>> getInterfaces(DefaultCQE request) {
        List<String> result = service.getInterfaces();
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<String>> getMethodNameByInterfaceName(GetMethodNameByInterfaceNameQuery request) {
        InterfaceName interfaceName = new InterfaceName(request.getInterfaceName());
        List<String> result = service.getMethodNameByInterfaceName(interfaceName);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Integer> initPowerInProStart(DefaultCQE request) {
        Integer result = service.initPowerInProStart();
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    protected BaseDoService<PowerDTO> getService() {
        return service;
    }
}
