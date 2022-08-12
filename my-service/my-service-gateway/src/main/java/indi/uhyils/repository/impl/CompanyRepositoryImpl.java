package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.CompanyAssembler;
import indi.uhyils.dao.CompanyDao;
import indi.uhyils.pojo.DO.CompanyDO;
import indi.uhyils.pojo.DTO.CompanyDTO;
import indi.uhyils.pojo.entity.Company;
import indi.uhyils.repository.CompanyRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 厂商表(Company)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年08月12日 08时33分
*/
@Repository
public class CompanyRepositoryImpl extends AbstractRepository<Company, CompanyDO, CompanyDao, CompanyDTO, CompanyAssembler> implements CompanyRepository {

    protected CompanyRepositoryImpl(CompanyAssembler convert, CompanyDao dao) {
        super(convert, dao);
    }


}
