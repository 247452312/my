package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.PlatformNodeLinkDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
* 节点关联表(PlatformNodeLink)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @date 文件创建日期 2022年03月11日 09时31分
*/
public class PlatformNodeLink extends AbstractDoEntity<PlatformNodeLinkDO> {

  @Default
  public PlatformNodeLink(PlatformNodeLinkDO data) {
      super(data);
  }

  public PlatformNodeLink(Long id) {
      super(id, new PlatformNodeLinkDO());
  }

}
