package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.DbInfoAssembler;
import indi.uhyils.dao.DbInfoDao;
import indi.uhyils.enum_.InterfaceTypeEnum;
import indi.uhyils.pojo.DO.DbInfoDO;
import indi.uhyils.pojo.DO.InterfaceInfoDO;
import indi.uhyils.pojo.DTO.DbInfoDTO;
import indi.uhyils.pojo.entity.DbInfo;
import indi.uhyils.pojo.entity.ProviderInfo;
import indi.uhyils.pojo.entity.interfaces.InterfaceInfo;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DbInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.Asserts;
import java.util.List;


/**
 * 数据库连接表(DbInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分07秒
 */
@Repository
public class DbInfoRepositoryImpl extends AbstractRepository<DbInfo, DbInfoDO, DbInfoDao, DbInfoDTO, DbInfoAssembler> implements DbInfoRepository {

    protected DbInfoRepositoryImpl(DbInfoAssembler convert, DbInfoDao dao) {
        super(convert, dao);
    }


    @Override
    public List<DbInfo> findByProvider(ProviderInfo provider) {
        Identifier providerId = provider.getUnique();
        return findByProviderId(providerId);

    }

    @Override
    public List<DbInfo> findByProviderId(Identifier providerId) {
        LambdaQueryWrapper<DbInfoDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DbInfoDO::getProviderId, providerId.getId());
        List<DbInfoDO> dbInfoDOS = dao.selectList(queryWrapper);
        return assembler.listToEntity(dbInfoDOS);
    }

    @Override
    public DbInfo findByInterfaceInfo(InterfaceInfo interfaceInfo) {
        InterfaceInfoDO interfaceInfoDO = interfaceInfo.toData();
        InterfaceTypeEnum parse = InterfaceTypeEnum.parse(interfaceInfoDO.getType());
        Asserts.assertTrue(parse == InterfaceTypeEnum.DB, "接口不是数据库类型,执行失败");
        Long markId = interfaceInfoDO.getMarkId();
        return this.find(new Identifier(markId));
    }
}
