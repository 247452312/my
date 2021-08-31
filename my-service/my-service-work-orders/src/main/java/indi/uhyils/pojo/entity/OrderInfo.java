package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderInfoDO;

/**
 * 工单基础信息样例表(OrderInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分13秒
 */
public class OrderInfo extends AbstractDoEntity<OrderInfoDO> {

    public OrderInfo(OrderInfoDO dO) {
        super(dO);
    }

    public OrderInfo(Long id) {
        super(id, new OrderInfoDO());
    }

}
