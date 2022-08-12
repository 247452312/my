package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ProviderInterfaceAssembler;
import indi.uhyils.dao.ProviderInterfaceDao;
import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceDTO;
import indi.uhyils.pojo.entity.ProviderInterface;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Repository
public class ProviderInterfaceRepositoryImpl extends AbstractRepository<ProviderInterface, ProviderInterfaceDO, ProviderInterfaceDao, ProviderInterfaceDTO, ProviderInterfaceAssembler> implements ProviderInterfaceRepository {

    protected ProviderInterfaceRepositoryImpl(ProviderInterfaceAssembler convert, ProviderInterfaceDao dao) {
        super(convert, dao);
    }


}
