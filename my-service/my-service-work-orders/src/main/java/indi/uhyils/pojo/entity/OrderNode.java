package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderNodeDO;

/**
 * 工单节点样例表(OrderNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分20秒
 */
public class OrderNode extends AbstractDoEntity<OrderNodeDO> {

    public OrderNode(OrderNodeDO dO) {
        super(dO);
    }

    public OrderNode(Long id) {
        super(id, new OrderNodeDO());
    }

}
