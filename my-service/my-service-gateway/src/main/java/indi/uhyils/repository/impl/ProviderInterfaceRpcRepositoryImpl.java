package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ProviderInterfaceRpcAssembler;
import indi.uhyils.dao.ProviderInterfaceRpcDao;
import indi.uhyils.pojo.DO.ProviderInterfaceRpcDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceRpcDTO;
import indi.uhyils.pojo.entity.ProviderExample;
import indi.uhyils.pojo.entity.ProviderInterfaceRpc;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ProviderInterfaceRpcRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * http接口子表(ProviderInterfaceRpc)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Repository
public class ProviderInterfaceRpcRepositoryImpl extends AbstractRepository<ProviderInterfaceRpc, ProviderInterfaceRpcDO, ProviderInterfaceRpcDao, ProviderInterfaceRpcDTO, ProviderInterfaceRpcAssembler> implements ProviderInterfaceRpcRepository {

    protected ProviderInterfaceRpcRepositoryImpl(ProviderInterfaceRpcAssembler convert, ProviderInterfaceRpcDao dao) {
        super(convert, dao);
    }


    @Override
    public ProviderExample findByProviderId(Identifier id) {
        LambdaQueryWrapper<ProviderInterfaceRpcDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProviderInterfaceRpcDO::getFid, id.getId());
        ProviderInterfaceRpcDO little = dao.selectOne(queryWrapper);
        return assembler.toEntity(little);
    }
}
