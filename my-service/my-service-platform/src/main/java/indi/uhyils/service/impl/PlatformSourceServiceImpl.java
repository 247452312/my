package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PlatformSourceAssembler;
import indi.uhyils.pojo.DO.PlatformSourceDO;
import indi.uhyils.pojo.DTO.PlatformSourceDTO;
import indi.uhyils.pojo.entity.PlatformSource;
import indi.uhyils.repository.PlatformSourceRepository;
import indi.uhyils.service.PlatformSourceService;
import org.springframework.stereotype.Service;

/**
* 资源主表(PlatformSource)表 内部服务实现类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Service
@ReadWriteMark(tables = {"sys_platform_source"})
public class PlatformSourceServiceImpl extends AbstractDoService<PlatformSourceDO, PlatformSource, PlatformSourceDTO, PlatformSourceRepository, PlatformSourceAssembler> implements PlatformSourceService {

    public PlatformSourceServiceImpl(PlatformSourceAssembler assembler, PlatformSourceRepository repository) {
        super(assembler, repository);
    }


}
