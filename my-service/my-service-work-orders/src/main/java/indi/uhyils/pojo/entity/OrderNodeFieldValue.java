package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分27秒
 */
public class OrderNodeFieldValue extends AbstractDoEntity<OrderNodeFieldValueDO> {

    public OrderNodeFieldValue(OrderNodeFieldValueDO dO) {
        super(dO);
    }

    public OrderNodeFieldValue(Long id) {
        super(id, new OrderNodeFieldValueDO());
    }

}
