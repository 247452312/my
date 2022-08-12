package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ProviderInterfaceParamDTO;
import indi.uhyils.protocol.rpc.ProviderInterfaceParamProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ProviderInterfaceParamService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 接口参数表(ProviderInterfaceParam)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@RpcService
public class ProviderInterfaceParamProviderImpl extends BaseDefaultProvider<ProviderInterfaceParamDTO> implements ProviderInterfaceParamProvider {


    @Autowired
    private ProviderInterfaceParamService service;


    @Override
    protected BaseDoService<ProviderInterfaceParamDTO> getService() {
        return service;
    }

}

