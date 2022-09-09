package indi.uhyils.repository;

import indi.uhyils.pojo.DO.CompanyDO;
import indi.uhyils.pojo.entity.Company;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 厂商表(Company)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public interface CompanyRepository extends BaseEntityRepository<CompanyDO, Company> {

    /**
     * 根据ak查询用户
     *
     * @param ak
     *
     * @return
     */
    Company findByAk(String ak);
}
