package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ConsumerPowerDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.AgreeForInterfacePowerCommand;
import indi.uhyils.pojo.cqe.command.ApplyForInterfacePowerCommand;
import indi.uhyils.protocol.rpc.ConsumerPowerProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ConsumerPowerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消费方权限表(ConsumerPower)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分07秒
 */
@RpcService
public class ConsumerPowerProviderImpl extends BaseDefaultProvider<ConsumerPowerDTO> implements ConsumerPowerProvider {


    @Autowired
    private ConsumerPowerService service;


    @Override
    protected BaseDoService<ConsumerPowerDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<Boolean> applyForInterfacePower(ApplyForInterfacePowerCommand command) {
        Boolean result = service.applyForInterfacePower(command);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> agreeForInterfacePower(AgreeForInterfacePowerCommand command) {
        Boolean result = service.agreeForInterfacePower(command);
        return ServiceResult.buildSuccessResult(result);
    }
}

