package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ProviderInterfaceHttpAssembler;
import indi.uhyils.dao.ProviderInterfaceHttpDao;
import indi.uhyils.pojo.DO.ProviderInterfaceHttpDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceHttpDTO;
import indi.uhyils.pojo.entity.ProviderExample;
import indi.uhyils.pojo.entity.ProviderInterfaceHttp;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ProviderInterfaceHttpRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * http接口子表(ProviderInterfaceHttp)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Repository
public class ProviderInterfaceHttpRepositoryImpl extends AbstractRepository<ProviderInterfaceHttp, ProviderInterfaceHttpDO, ProviderInterfaceHttpDao, ProviderInterfaceHttpDTO, ProviderInterfaceHttpAssembler> implements ProviderInterfaceHttpRepository {

    protected ProviderInterfaceHttpRepositoryImpl(ProviderInterfaceHttpAssembler convert, ProviderInterfaceHttpDao dao) {
        super(convert, dao);
    }


    @Override
    public ProviderExample findByProviderId(Identifier id) {
        return null;
    }
}
