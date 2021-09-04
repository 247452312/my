package indi.uhyils.repository;

import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.entity.OrderNode;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 工单节点样例表(OrderNode)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分20秒
 */
public interface OrderNodeRepository extends BaseEntityRepository<OrderNodeDO, OrderNode> {


    /**
     * 工单节点失败(主动将工单节点置为失败)(处理人员经过核实,客观上不能完成此操作,例:审批时客户填写不合格)
     *
     * @param orderNodeId
     * @param msg
     */
    void makeOrderFault(Long orderNodeId, String msg);

    /**
     * 获取下一个节点
     *
     * @param orderNode
     *
     * @return
     */
    OrderNode findNext(OrderNode orderNode);
}
