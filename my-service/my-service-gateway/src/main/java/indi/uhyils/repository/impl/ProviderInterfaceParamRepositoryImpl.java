package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.ProviderInterfaceParamAssembler;
import indi.uhyils.dao.ProviderInterfaceParamDao;
import indi.uhyils.pojo.DO.ProviderInterfaceParamDO;
import indi.uhyils.pojo.DTO.ProviderInterfaceParamDTO;
import indi.uhyils.pojo.entity.ProviderInterfaceParam;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ProviderInterfaceParamRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.List;


/**
 * 接口参数表(ProviderInterfaceParam)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Repository
public class ProviderInterfaceParamRepositoryImpl extends AbstractRepository<ProviderInterfaceParam, ProviderInterfaceParamDO, ProviderInterfaceParamDao, ProviderInterfaceParamDTO, ProviderInterfaceParamAssembler> implements ProviderInterfaceParamRepository {

    protected ProviderInterfaceParamRepositoryImpl(ProviderInterfaceParamAssembler convert, ProviderInterfaceParamDao dao) {
        super(convert, dao);
    }


    @Override
    public List<ProviderInterfaceParam> findByInterfaceId(Identifier interfaceId) {
        LambdaQueryWrapper<ProviderInterfaceParamDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProviderInterfaceParamDO::getProviderInterfaceId, interfaceId.getId());
        return assembler.listToEntity(dao.selectList(queryWrapper));
    }

}
