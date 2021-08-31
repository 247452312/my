package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderNodeRouteDO;

/**
 * 节点间关联路由样例表(OrderNodeRoute)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分33秒
 */
public class OrderNodeRoute extends AbstractDoEntity<OrderNodeRouteDO> {

    public OrderNodeRoute(OrderNodeRouteDO dO) {
        super(dO);
    }

    public OrderNodeRoute(Long id) {
        super(id, new OrderNodeRouteDO());
    }

}
