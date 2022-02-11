package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.DynamicCodeDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
* 动态代码主表(DynamicCode)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @date 文件创建日期 2022年02月11日 18时53分
*/
public class DynamicCode extends AbstractDoEntity<DynamicCodeDO> {

  @Default
  public DynamicCode(DynamicCodeDO data) {
      super(data);
  }

  public DynamicCode(Long id) {
      super(id, new DynamicCodeDO());
  }

}
