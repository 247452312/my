package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ProviderInterfaceMysqlAssembler;
import indi.uhyils.dao.ProviderInterfaceMysqlDao;
import indi.uhyils.pojo.DO.ProviderInterfaceMysqlDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceMysqlDTO;
import indi.uhyils.pojo.entity.ProviderInterfaceMysql;
import indi.uhyils.repository.ProviderInterfaceMysqlRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * mysql接口子表(ProviderInterfaceMysql)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Repository
public class ProviderInterfaceMysqlRepositoryImpl extends AbstractRepository<ProviderInterfaceMysql, ProviderInterfaceMysqlDO, ProviderInterfaceMysqlDao, ProviderInterfaceMysqlDTO, ProviderInterfaceMysqlAssembler> implements ProviderInterfaceMysqlRepository {

    protected ProviderInterfaceMysqlRepositoryImpl(ProviderInterfaceMysqlAssembler convert, ProviderInterfaceMysqlDao dao) {
        super(convert, dao);
    }


}
