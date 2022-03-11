package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PlatformSourceDbAssembler;
import indi.uhyils.pojo.DO.PlatformSourceDbDO;
import indi.uhyils.pojo.DTO.PlatformSourceDbDTO;
import indi.uhyils.pojo.entity.PlatformSourceDb;
import indi.uhyils.repository.PlatformSourceDbRepository;
import indi.uhyils.service.PlatformSourceDbService;
import org.springframework.stereotype.Service;

/**
* 数据库资源表(PlatformSourceDb)表 内部服务实现类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Service
@ReadWriteMark(tables = {"sys_platform_source_db"})
public class PlatformSourceDbServiceImpl extends AbstractDoService<PlatformSourceDbDO, PlatformSourceDb, PlatformSourceDbDTO, PlatformSourceDbRepository, PlatformSourceDbAssembler> implements PlatformSourceDbService {

    public PlatformSourceDbServiceImpl(PlatformSourceDbAssembler assembler, PlatformSourceDbRepository repository) {
        super(assembler, repository);
    }


}
