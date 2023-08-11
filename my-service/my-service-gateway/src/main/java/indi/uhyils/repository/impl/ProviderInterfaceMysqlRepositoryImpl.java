package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ProviderInterfaceMysqlAssembler;
import indi.uhyils.dao.ProviderInterfaceMysqlDao;
import indi.uhyils.pojo.DO.ProviderInterfaceMysqlDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceMysqlDTO;
import indi.uhyils.pojo.entity.ProviderExample;
import indi.uhyils.pojo.entity.ProviderInterfaceMysql;
import indi.uhyils.pojo.entity.type.Identifier;
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


    @Override
    public ProviderExample findByProviderId(Identifier id) {
        LambdaQueryWrapper<ProviderInterfaceMysqlDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProviderInterfaceMysqlDO::getFid, id.getId());
        ProviderInterfaceMysqlDO little = dao.selectOne(queryWrapper);
        return assembler.toEntity(little);
    }
}
