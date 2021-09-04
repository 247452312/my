package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.pojo.DTO.OrderNodeFieldValueDTO;
import indi.uhyils.pojo.entity.OrderNodeFieldValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分27秒
 */
@Assembler
public class OrderNodeFieldValueAssembler extends AbstractAssembler<OrderNodeFieldValueDO, OrderNodeFieldValue, OrderNodeFieldValueDTO> {

    /**
     * 创建一个工单节点属性的真实值
     *
     * @param orderFieldId 工单节点属性的id
     * @param value        用户填写的值
     *
     * @return
     */
    private OrderNodeFieldValueDTO buildOrderNodeFieldValue(Long orderFieldId, String value) {
        OrderNodeFieldValueDTO fieldValue = new OrderNodeFieldValueDTO();
        fieldValue.setNodeFieldId(orderFieldId);
        fieldValue.setRealValue(value);
        return fieldValue;
    }

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

    public List<OrderNodeFieldValueDTO> valueToValueDTO(Map<Long, String> value) {
        List<OrderNodeFieldValueDTO> result = new ArrayList<>(value.size());
        for (Map.Entry<Long, String> entry : value.entrySet()) {
            result.add(buildOrderNodeFieldValue(entry.getKey(), entry.getValue()));
        }
        return result;
    }
}

