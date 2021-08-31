package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.pojo.DTO.OrderNodeFieldValueDTO;
import indi.uhyils.pojo.entity.OrderNodeFieldValue;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分27秒
 */
@Assembler
public class OrderNodeFieldValueAssembler extends AbstractAssembler<OrderNodeFieldValueDO, OrderNodeFieldValue, OrderNodeFieldValueDTO> {

    @Override
    public OrderNodeFieldValue toEntity(OrderNodeFieldValueDO dO) {
        return new OrderNodeFieldValue(dO);
    }

    @Override
    public OrderNodeFieldValue toEntity(OrderNodeFieldValueDTO dto) {
        return new OrderNodeFieldValue(toDo(dto));
    }

    @Override
    protected Class<OrderNodeFieldValueDO> getDoClass() {
        return OrderNodeFieldValueDO.class;
    }

    @Override
    protected Class<OrderNodeFieldValueDTO> getDtoClass() {
        return OrderNodeFieldValueDTO.class;
    }
}

