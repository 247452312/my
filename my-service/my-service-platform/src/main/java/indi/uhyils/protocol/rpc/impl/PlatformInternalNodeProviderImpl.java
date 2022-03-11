package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PlatformInternalNodeDTO;
import indi.uhyils.protocol.rpc.PlatformInternalNodeProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PlatformInternalNodeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 内部节点表(PlatformInternalNode)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@RpcService
public class PlatformInternalNodeProviderImpl extends BaseDefaultProvider<PlatformInternalNodeDTO> implements PlatformInternalNodeProvider {


    @Autowired
    private PlatformInternalNodeService service;
    
    
    @Override
    protected BaseDoService<PlatformInternalNodeDTO> getService() {
        return service;
    }

}

