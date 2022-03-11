package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PlatformPublishNodeDTO;
import indi.uhyils.protocol.rpc.PlatformPublishNodeProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PlatformPublishNodeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 发布节点表(PlatformPublishNode)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@RpcService
public class PlatformPublishNodeProviderImpl extends BaseDefaultProvider<PlatformPublishNodeDTO> implements PlatformPublishNodeProvider {


    @Autowired
    private PlatformPublishNodeService service;
    
    
    @Override
    protected BaseDoService<PlatformPublishNodeDTO> getService() {
        return service;
    }

}

