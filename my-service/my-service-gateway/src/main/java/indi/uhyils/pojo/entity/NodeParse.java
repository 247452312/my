package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.NodeParseDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 转换节点解析表(NodeParse)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public class NodeParse extends AbstractDoEntity<NodeParseDO> {

    @Default
    public NodeParse(NodeParseDO data) {
        super(data);
    }

    public NodeParse(Long id) {
        super(id, new NodeParseDO());
    }

}
