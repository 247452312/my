package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ProviderInterfaceHttpDTO;
import indi.uhyils.protocol.rpc.ProviderInterfaceHttpProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ProviderInterfaceHttpService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * http接口子表(ProviderInterfaceHttp)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@RpcService
public class ProviderInterfaceHttpProviderImpl extends BaseDefaultProvider<ProviderInterfaceHttpDTO> implements ProviderInterfaceHttpProvider {


    @Autowired
    private ProviderInterfaceHttpService service;


    @Override
    protected BaseDoService<ProviderInterfaceHttpDTO> getService() {
        return service;
    }

}

