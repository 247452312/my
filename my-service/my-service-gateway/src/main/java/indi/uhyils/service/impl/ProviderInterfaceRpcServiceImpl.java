package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ProviderInterfaceRpcAssembler;
import indi.uhyils.pojo.DO.ProviderInterfaceRpcDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceRpcDTO;
import indi.uhyils.pojo.entity.ProviderInterfaceRpc;
import indi.uhyils.repository.ProviderInterfaceRpcRepository;
import indi.uhyils.service.ProviderInterfaceRpcService;
import org.springframework.stereotype.Service;

/**
 * http接口子表(ProviderInterfaceRpc)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Service
@ReadWriteMark(tables = {"sys_provider_interface_rpc"})
public class ProviderInterfaceRpcServiceImpl extends AbstractDoService<ProviderInterfaceRpcDO, ProviderInterfaceRpc, ProviderInterfaceRpcDTO, ProviderInterfaceRpcRepository, ProviderInterfaceRpcAssembler> implements ProviderInterfaceRpcService {

    public ProviderInterfaceRpcServiceImpl(ProviderInterfaceRpcAssembler assembler, ProviderInterfaceRpcRepository repository) {
        super(assembler, repository);
    }


}
