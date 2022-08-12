package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ProviderInterfaceParamAssembler;
import indi.uhyils.dao.ProviderInterfaceParamDao;
import indi.uhyils.pojo.DO.ProviderInterfaceParamDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceParamDTO;
import indi.uhyils.pojo.entity.ProviderInterfaceParam;
import indi.uhyils.repository.ProviderInterfaceParamRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 接口参数表(ProviderInterfaceParam)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年08月12日 08时33分
*/
@Repository
public class ProviderInterfaceParamRepositoryImpl extends AbstractRepository<ProviderInterfaceParam, ProviderInterfaceParamDO, ProviderInterfaceParamDao, ProviderInterfaceParamDTO, ProviderInterfaceParamAssembler> implements ProviderInterfaceParamRepository {

    protected ProviderInterfaceParamRepositoryImpl(ProviderInterfaceParamAssembler convert, ProviderInterfaceParamDao dao) {
        super(convert, dao);
    }


}
