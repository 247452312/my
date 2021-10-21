package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ProviderInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.RegisterProviderCommand;
import indi.uhyils.protocol.rpc.ProviderInfoProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ProviderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 服务提供者表(ProviderInfo)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@RpcService
public class ProviderInfoProviderImpl extends BaseDefaultProvider<ProviderInfoDTO> implements ProviderInfoProvider {


    @Autowired
    private ProviderInfoService service;


    @Override
    protected BaseDoService<ProviderInfoDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<ProviderInfoDTO> registerProvider(RegisterProviderCommand command) {
        ProviderInfoDTO result = service.registerProvider(command);
        return ServiceResult.buildSuccessResult(result);
    }
}

