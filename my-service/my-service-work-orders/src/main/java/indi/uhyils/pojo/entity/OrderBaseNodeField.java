package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.OrderBaseNodeFieldDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 工单节点属性样例表(OrderBaseNodeField)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分00秒
 */
public class OrderBaseNodeField extends AbstractDoEntity<OrderBaseNodeFieldDO> {

    @Default
    public OrderBaseNodeField(OrderBaseNodeFieldDO data) {
        super(data);
    }

    public OrderBaseNodeField(Long id) {
        super(id, new OrderBaseNodeFieldDO());
    }

}
