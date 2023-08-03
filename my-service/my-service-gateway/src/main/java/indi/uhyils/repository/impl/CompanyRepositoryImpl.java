package indi.uhyils.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.CompanyAssembler;
import indi.uhyils.dao.CompanyDao;
import indi.uhyils.pojo.DO.CompanyDO;
import indi.uhyils.pojo.DTO.CompanyDTO;
import indi.uhyils.pojo.entity.Company;
import indi.uhyils.repository.CompanyRepository;
import indi.uhyils.repository.base.AbstractRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.StringUtil;


/**
 * 厂商表(Company)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Repository
public class CompanyRepositoryImpl extends AbstractRepository<Company, CompanyDO, CompanyDao, CompanyDTO, CompanyAssembler> implements CompanyRepository {

    protected CompanyRepositoryImpl(CompanyAssembler convert, CompanyDao dao) {
        super(convert, dao);
    }


    @Override
    public Company findByAk(String ak) {
        Asserts.assertTrue(StringUtil.isNotEmpty(ak), "用户名不存在,厂商登录失败!");
        LambdaQueryWrapper<CompanyDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(CompanyDO::getAk, ak);
        CompanyDO companyDO = dao.selectOne(queryWrapper);
        return assembler.toEntity(companyDO);
    }
}
