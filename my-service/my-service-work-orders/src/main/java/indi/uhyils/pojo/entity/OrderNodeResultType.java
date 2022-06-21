package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 工单节点处理结果样例表(OrderNodeResultType)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分30秒
 */
public class OrderNodeResultType extends AbstractDoEntity<OrderNodeResultTypeDO> {

    @Default
    public OrderNodeResultType(OrderNodeResultTypeDO data) {
        super(data);
    }

    public OrderNodeResultType(Long id) {
        super(id, new OrderNodeResultTypeDO());
    }

}
