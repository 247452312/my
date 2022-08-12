package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时33分
 */
public class CallNode extends AbstractDoEntity<CallNodeDO> {

    @Default
    public CallNode(CallNodeDO data) {
        super(data);
    }

    public CallNode(Long id) {
        super(id, new CallNodeDO());
    }

}
