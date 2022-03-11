package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.PlatformSourceDbDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
* 数据库资源表(PlatformSourceDb)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @date 文件创建日期 2022年03月11日 09时31分
*/
public class PlatformSourceDb extends AbstractDoEntity<PlatformSourceDbDO> {

  @Default
  public PlatformSourceDb(PlatformSourceDbDO data) {
      super(data);
  }

  public PlatformSourceDb(Long id) {
      super(id, new PlatformSourceDbDO());
  }

}
