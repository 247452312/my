package indi.uhyils.repository;

import indi.uhyils.pojo.DO.NodeDO;
import indi.uhyils.pojo.entity.AbstractDataNode;
import indi.uhyils.pojo.entity.Node;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 转换节点表(Node)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public interface NodeRepository extends BaseEntityRepository<NodeDO, Node> {

    /**
     * 根据path获取中间节点或者底层节点
     *
     * @param database
     * @param table
     */
    AbstractDataNode findNodeOrProvider(String database, String table);

}
