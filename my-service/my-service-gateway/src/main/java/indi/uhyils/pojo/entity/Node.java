package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.NodeDO;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.util.Asserts;

/**
 * 转换节点表(Node)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月09日 15时45分
 */
public class Node extends AbstractDataNode<NodeDO> {

    @Default
    public Node(NodeDO data) {
        super(data);
    }

    public Node(Long id) {
        super(id, new NodeDO());
    }

    @Override
    public String databaseName() {
        return toData().orElseThrow(() -> Asserts.makeException("未填充内容")).getDatabase();
    }

    @Override
    public String tableName() {
        return toData().orElseThrow(() -> Asserts.makeException("未填充内容")).getTableName();
    }

    /**
     * 填充子节点
     *
     * @param nodeRepository
     * @param providerInterfaceRepository
     */
    public void fillSubNode(NodeRepository nodeRepository, ProviderInterfaceRepository providerInterfaceRepository) {

    }
}
