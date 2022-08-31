package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.util.Asserts;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public class CallNode extends AbstractDataNode<CallNodeDO> {

    @Default
    public CallNode(CallNodeDO data) {
        super(data);
    }

    public CallNode(Long id) {
        super(id, new CallNodeDO());
    }

    @Override
    public String databaseName() {
        final CallNodeDO node = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        final String url = node.getUrl();
        final int firstIndex = url.indexOf("/");
        return url.substring(0, firstIndex);
    }

    @Override
    public String tableName() {
        final CallNodeDO node = toData().orElseThrow(() -> Asserts.makeException("未填充内容"));
        final String url = node.getUrl();
        final int firstIndex = url.indexOf("/");
        return url.substring(firstIndex + 1);
    }
}
