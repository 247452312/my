package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.CompanyPowerAssembler;
import indi.uhyils.dao.CompanyPowerDao;
import indi.uhyils.pojo.DO.CompanyPowerDO;
import indi.uhyils.pojo.DTO.CompanyPowerDTO;
import indi.uhyils.pojo.entity.CompanyPower;
import indi.uhyils.repository.CompanyPowerRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 厂商接口调用权限表(CompanyPower)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Repository
public class CompanyPowerRepositoryImpl extends AbstractRepository<CompanyPower, CompanyPowerDO, CompanyPowerDao, CompanyPowerDTO, CompanyPowerAssembler> implements CompanyPowerRepository {

    protected CompanyPowerRepositoryImpl(CompanyPowerAssembler convert, CompanyPowerDao dao) {
        super(convert, dao);
    }


}
