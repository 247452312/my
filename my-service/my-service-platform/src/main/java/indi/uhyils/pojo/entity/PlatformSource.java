package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.PlatformSourceDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
* 资源主表(PlatformSource)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @date 文件创建日期 2022年03月11日 09时31分
*/
public class PlatformSource extends AbstractDoEntity<PlatformSourceDO> {

  @Default
  public PlatformSource(PlatformSourceDO data) {
      super(data);
  }

  public PlatformSource(Long id) {
      super(id, new PlatformSourceDO());
  }

}
