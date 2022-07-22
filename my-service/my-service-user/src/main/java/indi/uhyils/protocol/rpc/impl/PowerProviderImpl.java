package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.Public;
import indi.uhyils.pojo.DTO.PowerDTO;
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
    public List<PowerDTO> getPowers(DefaultCQE request) {
        return service.getPowers();

    }

    @Override
    @Public
    public Boolean checkUserHavePower(CheckUserHavePowerQuery request) {
        InterfaceName interfaceName = new InterfaceName(request.getInterfaceName());
        MethodName methodName = new MethodName(request.getMethodName());
        Identifier userId = new Identifier(request.getUserId());
        return service.checkUserHavePower(interfaceName, methodName, userId);
    }

    @Override
    public Boolean deletePower(IdCommand request) {
        Identifier powerId = new Identifier(request.getId());
        return service.deletePower(powerId);

    }

    @Override
    public List<String> getInterfaces(DefaultCQE request) {
        return service.getInterfaces();

    }

    @Override
    public List<String> getMethodNameByInterfaceName(GetMethodNameByInterfaceNameQuery request) {
        InterfaceName interfaceName = new InterfaceName(request.getInterfaceName());
        return service.getMethodNameByInterfaceName(interfaceName);

    }

    @Override
    public Integer initPowerInProStart(DefaultCQE request) {
        return service.initPowerInProStart();

    }

    @Override
    protected BaseDoService<PowerDTO> getService() {
        return service;
    }
}
