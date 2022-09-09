package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.NodeLinkDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 中间节点与外部节点关联关系(NodeLink)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class NodeLink extends AbstractDoEntity<NodeLinkDO> {

    @Default
    public NodeLink(NodeLinkDO data) {
        super(data);
    }

    public NodeLink(Long id) {
        super(id, new NodeLinkDO());
    }

}
