package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ProviderInterfaceAssembler;
import indi.uhyils.dao.ProviderInterfaceDao;
import indi.uhyils.pojo.DO.ProviderInterfaceDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceDTO;
import indi.uhyils.pojo.entity.AbstractDataNode;
import indi.uhyils.pojo.entity.ProviderInterface;
import indi.uhyils.pojo.entity.ProviderInterfaceParam;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ProviderInterfaceParamRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.List;
import javax.annotation.Resource;


/**
 * 接口表,提供方提供的调用方式以及url(ProviderInterface)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Repository
public class ProviderInterfaceRepositoryImpl extends AbstractRepository<ProviderInterface, ProviderInterfaceDO, ProviderInterfaceDao, ProviderInterfaceDTO, ProviderInterfaceAssembler> implements ProviderInterfaceRepository {

    @Resource
    private ProviderInterfaceParamRepository paramRepository;

    protected ProviderInterfaceRepositoryImpl(ProviderInterfaceAssembler convert, ProviderInterfaceDao dao) {
        super(convert, dao);
    }


    @Override
    public ProviderInterface find(Integer invokeType, String database, String table) {
        LambdaQueryWrapper<ProviderInterfaceDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProviderInterfaceDO::getInvokeType, invokeType);
        queryWrapper.eq(ProviderInterfaceDO::getDatabase, database);
        queryWrapper.eq(ProviderInterfaceDO::getTable, table);
        return assembler.toEntity(dao.selectOne(queryWrapper));
    }

    @Override
    public AbstractDataNode<ProviderInterfaceDO> find(String database, String table) {
        LambdaQueryWrapper<ProviderInterfaceDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProviderInterfaceDO::getDatabase, database);
        queryWrapper.eq(ProviderInterfaceDO::getTable, table);
        ProviderInterfaceDO nodeDO = dao.selectOne(queryWrapper);
        return assembler.toEntity(nodeDO);
    }

    @Override
    public List<ProviderInterfaceParam> findParamByInterfaceId(Identifier id) {
        return paramRepository.findByInterfaceId(id);
    }
}
