package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PlatformSourceDTO;
import indi.uhyils.protocol.rpc.PlatformSourceProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PlatformSourceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 资源主表(PlatformSource)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@RpcService
public class PlatformSourceProviderImpl extends BaseDefaultProvider<PlatformSourceDTO> implements PlatformSourceProvider {


    @Autowired
    private PlatformSourceService service;
    
    
    @Override
    protected BaseDoService<PlatformSourceDTO> getService() {
        return service;
    }

}

