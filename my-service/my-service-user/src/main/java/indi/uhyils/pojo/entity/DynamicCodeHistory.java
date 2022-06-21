package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.DynamicCodeHistoryDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
* 动态代码历史记录表(DynamicCodeHistory)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @date 文件创建日期 2022年02月11日 18时53分
*/
public class DynamicCodeHistory extends AbstractDoEntity<DynamicCodeHistoryDO> {

  @Default
  public DynamicCodeHistory(DynamicCodeHistoryDO data) {
      super(data);
  }

  public DynamicCodeHistory(Long id) {
      super(id, new DynamicCodeHistoryDO());
  }

}
