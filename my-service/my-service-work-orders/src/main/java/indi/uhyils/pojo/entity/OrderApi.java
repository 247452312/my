package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderApiDO;

/**
 * 节点api表(OrderApi)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时58分45秒
 */
public class OrderApi extends AbstractDoEntity<OrderApiDO> {

    public OrderApi(OrderApiDO dO) {
        super(dO);
    }

    public OrderApi(Long id) {
        super(id, new OrderApiDO());
    }

}
