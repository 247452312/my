package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderBaseInfoDO;

/**
 * 工单基础信息样例表(OrderBaseInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时58分53秒
 */
public class OrderBaseInfo extends AbstractDoEntity<OrderBaseInfoDO> {

    public OrderBaseInfo(OrderBaseInfoDO dO) {
        super(dO);
    }

    public OrderBaseInfo(Long id) {
        super(id, new OrderBaseInfoDO());
    }

}
