package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PlatformSourceInterfaceDTO;
import indi.uhyils.protocol.rpc.PlatformSourceInterfaceProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PlatformSourceInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 接口资源表(PlatformSourceInterface)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@RpcService
public class PlatformSourceInterfaceProviderImpl extends BaseDefaultProvider<PlatformSourceInterfaceDTO> implements PlatformSourceInterfaceProvider {


    @Autowired
    private PlatformSourceInterfaceService service;
    
    
    @Override
    protected BaseDoService<PlatformSourceInterfaceDTO> getService() {
        return service;
    }

}

