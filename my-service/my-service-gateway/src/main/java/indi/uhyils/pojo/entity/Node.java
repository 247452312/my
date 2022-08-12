package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.NodeDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
* 转换节点表(Node)表 数据库实体类
*
* @author uhyils <247452312@qq.com>
* @date 文件创建日期 2022年08月12日 08时33分
*/
public class Node extends AbstractDoEntity<NodeDO> {

  @Default
  public Node(NodeDO data) {
      super(data);
  }

  public Node(Long id) {
      super(id, new NodeDO());
  }

}
