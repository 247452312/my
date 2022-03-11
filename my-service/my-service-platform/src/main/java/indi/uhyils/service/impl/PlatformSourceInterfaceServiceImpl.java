package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PlatformSourceInterfaceAssembler;
import indi.uhyils.pojo.DO.PlatformSourceInterfaceDO;
import indi.uhyils.pojo.DTO.PlatformSourceInterfaceDTO;
import indi.uhyils.pojo.entity.PlatformSourceInterface;
import indi.uhyils.repository.PlatformSourceInterfaceRepository;
import indi.uhyils.service.PlatformSourceInterfaceService;
import org.springframework.stereotype.Service;

/**
* 接口资源表(PlatformSourceInterface)表 内部服务实现类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Service
@ReadWriteMark(tables = {"sys_platform_source_interface"})
public class PlatformSourceInterfaceServiceImpl extends AbstractDoService<PlatformSourceInterfaceDO, PlatformSourceInterface, PlatformSourceInterfaceDTO, PlatformSourceInterfaceRepository, PlatformSourceInterfaceAssembler> implements PlatformSourceInterfaceService {

    public PlatformSourceInterfaceServiceImpl(PlatformSourceInterfaceAssembler assembler, PlatformSourceInterfaceRepository repository) {
        super(assembler, repository);
    }


}
