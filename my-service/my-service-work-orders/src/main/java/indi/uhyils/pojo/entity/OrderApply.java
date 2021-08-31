package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderApplyDO;

/**
 * (OrderApply)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时58分49秒
 */
public class OrderApply extends AbstractDoEntity<OrderApplyDO> {

    public OrderApply(OrderApplyDO dO) {
        super(dO);
    }

    public OrderApply(Long id) {
        super(id, new OrderApplyDO());
    }

}
