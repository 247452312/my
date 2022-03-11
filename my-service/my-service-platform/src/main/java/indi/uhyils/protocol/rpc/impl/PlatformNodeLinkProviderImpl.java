package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.PlatformNodeLinkDTO;
import indi.uhyils.protocol.rpc.PlatformNodeLinkProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.PlatformNodeLinkService;
import org.springframework.beans.factory.annotation.Autowired;

/**
* 节点关联表(PlatformNodeLink)表 RPC对外访问实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@RpcService
public class PlatformNodeLinkProviderImpl extends BaseDefaultProvider<PlatformNodeLinkDTO> implements PlatformNodeLinkProvider {


    @Autowired
    private PlatformNodeLinkService service;
    
    
    @Override
    protected BaseDoService<PlatformNodeLinkDTO> getService() {
        return service;
    }

}

