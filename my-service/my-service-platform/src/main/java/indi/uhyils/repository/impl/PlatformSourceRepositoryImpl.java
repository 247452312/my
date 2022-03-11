package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PlatformSourceAssembler;
import indi.uhyils.dao.PlatformSourceDao;
import indi.uhyils.pojo.DO.PlatformSourceDO;
import indi.uhyils.pojo.DTO.PlatformSourceDTO;
import indi.uhyils.pojo.entity.PlatformSource;
import indi.uhyils.repository.PlatformSourceRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 资源主表(PlatformSource)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Repository
public class PlatformSourceRepositoryImpl extends AbstractRepository<PlatformSource, PlatformSourceDO, PlatformSourceDao, PlatformSourceDTO, PlatformSourceAssembler> implements PlatformSourceRepository {

    protected PlatformSourceRepositoryImpl(PlatformSourceAssembler convert, PlatformSourceDao dao) {
        super(convert, dao);
    }


}
