package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PlatformSourceDbDTO;
import indi.uhyils.protocol.rpc.PlatformSourceDbProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PlatformSourceDbService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 数据库资源表(PlatformSourceDb)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@RpcService
public class PlatformSourceDbProviderImpl extends BaseDefaultProvider<PlatformSourceDbDTO> implements PlatformSourceDbProvider {


    @Autowired
    private PlatformSourceDbService service;
    
    
    @Override
    protected BaseDoService<PlatformSourceDbDTO> getService() {
        return service;
    }

}

