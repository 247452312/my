package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.ProviderInterfaceAssembler;
import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceDTO;
import indi.uhyils.pojo.entity.ProviderInterface;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.service.ProviderInterfaceService;
import org.springframework.stereotype.Service;

/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Service
@ReadWriteMark(tables = {"sys_provider_interface"})
public class ProviderInterfaceServiceImpl extends AbstractDoService<ProviderInterfaceDO, ProviderInterface, ProviderInterfaceDTO, ProviderInterfaceRepository, ProviderInterfaceAssembler> implements ProviderInterfaceService {

    public ProviderInterfaceServiceImpl(ProviderInterfaceAssembler assembler, ProviderInterfaceRepository repository) {
        super(assembler, repository);
    }


}
