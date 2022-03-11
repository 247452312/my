package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PlatformSourceInterfaceAssembler;
import indi.uhyils.dao.PlatformSourceInterfaceDao;
import indi.uhyils.pojo.DO.PlatformSourceInterfaceDO;
import indi.uhyils.pojo.DTO.PlatformSourceInterfaceDTO;
import indi.uhyils.pojo.entity.PlatformSourceInterface;
import indi.uhyils.repository.PlatformSourceInterfaceRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 接口资源表(PlatformSourceInterface)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Repository
public class PlatformSourceInterfaceRepositoryImpl extends AbstractRepository<PlatformSourceInterface, PlatformSourceInterfaceDO, PlatformSourceInterfaceDao, PlatformSourceInterfaceDTO, PlatformSourceInterfaceAssembler> implements PlatformSourceInterfaceRepository {

    protected PlatformSourceInterfaceRepositoryImpl(PlatformSourceInterfaceAssembler convert, PlatformSourceInterfaceDao dao) {
        super(convert, dao);
    }


}
