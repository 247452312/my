package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderLogDO;

/**
 * 日志表(OrderLog)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分16秒
 */
public class OrderLog extends AbstractDoEntity<OrderLogDO> {

    public OrderLog(OrderLogDO dO) {
        super(dO);
    }

    public OrderLog(Long id) {
        super(id, new OrderLogDO());
    }

}
