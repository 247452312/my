package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.PlatformPowerDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
* 权限表(PlatformPower)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @date 文件创建日期 2022年03月11日 09时31分
*/
public class PlatformPower extends AbstractDoEntity<PlatformPowerDO> {

  @Default
  public PlatformPower(PlatformPowerDO data) {
      super(data);
  }

  public PlatformPower(Long id) {
      super(id, new PlatformPowerDO());
  }

}
