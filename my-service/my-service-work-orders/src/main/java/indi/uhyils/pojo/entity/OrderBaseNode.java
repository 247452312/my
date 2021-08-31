package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderBaseNodeDO;

/**
 * 工单节点样例表(OrderBaseNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时58分57秒
 */
public class OrderBaseNode extends AbstractDoEntity<OrderBaseNodeDO> {

    public OrderBaseNode(OrderBaseNodeDO dO) {
        super(dO);
    }

    public OrderBaseNode(Long id) {
        super(id, new OrderBaseNodeDO());
    }

}
