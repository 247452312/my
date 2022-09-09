package indi.uhyils.repository;

import indi.uhyils.pojo.DO.NodeLinkDO;
import indi.uhyils.pojo.entity.NodeLink;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 中间节点与外部节点关联关系(NodeLink)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public interface NodeLinkRepository extends BaseEntityRepository<NodeLinkDO, NodeLink> {

}
