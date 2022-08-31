package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.NodeDO;
import indi.uhyils.util.Asserts;

/**
 * 转换节点表(Node)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时33分
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

        final NodeDO node = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        final String url = node.getUrl();
        final int firstIndex = url.indexOf("/");
        return url.substring(0, firstIndex);
    }

    @Override
    public String tableName() {
        final NodeDO node = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        final String url = node.getUrl();
        final int firstIndex = url.indexOf("/");
        return url.substring(firstIndex + 1);
    }
}
