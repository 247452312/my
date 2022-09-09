package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.ProviderInterfaceMysqlDTO;
import indi.uhyils.protocol.rpc.ProviderInterfaceMysqlProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.ProviderInterfaceMysqlService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * mysql接口子表(ProviderInterfaceMysql)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@RpcService
public class ProviderInterfaceMysqlProviderImpl extends BaseDefaultProvider<ProviderInterfaceMysqlDTO> implements ProviderInterfaceMysqlProvider {


    @Autowired
    private ProviderInterfaceMysqlService service;


    @Override
    protected BaseDoService<ProviderInterfaceMysqlDTO> getService() {
        return service;
    }

}

