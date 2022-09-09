package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ProviderInterfaceRpcDTO;
import indi.uhyils.protocol.rpc.ProviderInterfaceRpcProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ProviderInterfaceRpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * http接口子表(ProviderInterfaceRpc)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@RpcService
public class ProviderInterfaceRpcProviderImpl extends BaseDefaultProvider<ProviderInterfaceRpcDTO> implements ProviderInterfaceRpcProvider {


    @Autowired
    private ProviderInterfaceRpcService service;


    @Override
    protected BaseDoService<ProviderInterfaceRpcDTO> getService() {
        return service;
    }

}

