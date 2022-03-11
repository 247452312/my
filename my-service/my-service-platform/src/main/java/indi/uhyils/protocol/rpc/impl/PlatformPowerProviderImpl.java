package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PlatformPowerDTO;
import indi.uhyils.protocol.rpc.PlatformPowerProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PlatformPowerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 权限表(PlatformPower)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@RpcService
public class PlatformPowerProviderImpl extends BaseDefaultProvider<PlatformPowerDTO> implements PlatformPowerProvider {


    @Autowired
    private PlatformPowerService service;
    
    
    @Override
    protected BaseDoService<PlatformPowerDTO> getService() {
        return service;
    }

}

