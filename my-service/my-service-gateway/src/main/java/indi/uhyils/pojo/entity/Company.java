package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.CompanyDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
* 厂商表(Company)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @date 文件创建日期 2022年08月12日 08时33分
*/
public class Company extends AbstractDoEntity<CompanyDO> {

  @Default
  public Company(CompanyDO data) {
      super(data);
  }

  public Company(Long id) {
      super(id, new CompanyDO());
  }

}
