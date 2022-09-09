package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.CompanyPowerDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 厂商接口调用权限表(CompanyPower)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class CompanyPower extends AbstractDoEntity<CompanyPowerDO> {

    @Default
    public CompanyPower(CompanyPowerDO data) {
        super(data);
    }

    public CompanyPower(Long id) {
        super(id, new CompanyPowerDO());
    }

}
