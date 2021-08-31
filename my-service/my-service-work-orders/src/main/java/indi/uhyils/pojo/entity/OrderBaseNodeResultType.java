package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderBaseNodeResultTypeDO;

/**
 * 工单节点处理结果样例表(OrderBaseNodeResultType)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分04秒
 */
public class OrderBaseNodeResultType extends AbstractDoEntity<OrderBaseNodeResultTypeDO> {

    public OrderBaseNodeResultType(OrderBaseNodeResultTypeDO dO) {
        super(dO);
    }

    public OrderBaseNodeResultType(Long id) {
        super(id, new OrderBaseNodeResultTypeDO());
    }

}
