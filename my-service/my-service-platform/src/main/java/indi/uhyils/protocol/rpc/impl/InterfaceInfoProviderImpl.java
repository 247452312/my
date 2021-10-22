package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.InterfaceInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.AddInterfaceCommand;
import indi.uhyils.protocol.rpc.InterfaceInfoProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.InterfaceInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接口信息表(InterfaceInfo)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@RpcService
public class InterfaceInfoProviderImpl extends BaseDefaultProvider<InterfaceInfoDTO> implements InterfaceInfoProvider {


    @Autowired
    private InterfaceInfoService service;


    @Override
    protected BaseDoService<InterfaceInfoDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<InterfaceInfoDTO> addInterface(AddInterfaceCommand command) {
        InterfaceInfoDTO result = service.addInterface(command);
        return ServiceResult.buildSuccessResult(result);
    }
}

