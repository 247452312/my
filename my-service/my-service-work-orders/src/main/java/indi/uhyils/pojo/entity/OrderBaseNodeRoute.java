package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderBaseNodeRouteDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 节点间关联路由样例表(OrderBaseNodeRoute)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分07秒
 */
public class OrderBaseNodeRoute extends AbstractDoEntity<OrderBaseNodeRouteDO> {

    public OrderBaseNodeRoute(OrderBaseNodeRouteDO dO) {
        super(dO);
    }

    public OrderBaseNodeRoute(Long id) {
        super(id, new OrderBaseNodeRouteDO());
    }

}
