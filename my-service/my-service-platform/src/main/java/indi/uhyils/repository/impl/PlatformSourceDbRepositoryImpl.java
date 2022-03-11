package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PlatformSourceDbAssembler;
import indi.uhyils.dao.PlatformSourceDbDao;
import indi.uhyils.pojo.DO.PlatformSourceDbDO;
import indi.uhyils.pojo.DTO.PlatformSourceDbDTO;
import indi.uhyils.pojo.entity.PlatformSourceDb;
import indi.uhyils.repository.PlatformSourceDbRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 数据库资源表(PlatformSourceDb)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Repository
public class PlatformSourceDbRepositoryImpl extends AbstractRepository<PlatformSourceDb, PlatformSourceDbDO, PlatformSourceDbDao, PlatformSourceDbDTO, PlatformSourceDbAssembler> implements PlatformSourceDbRepository {

    protected PlatformSourceDbRepositoryImpl(PlatformSourceDbAssembler convert, PlatformSourceDbDao dao) {
        super(convert, dao);
    }


}
