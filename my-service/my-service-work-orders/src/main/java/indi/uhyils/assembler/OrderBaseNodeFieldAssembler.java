package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderBaseNodeFieldDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeFieldDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeField;

/**
 * 工单节点属性样例表(OrderBaseNodeField)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分00秒
 */
@Assembler
public class OrderBaseNodeFieldAssembler extends AbstractAssembler<OrderBaseNodeFieldDO, OrderBaseNodeField, OrderBaseNodeFieldDTO> {

    @Override
    public OrderBaseNodeField toEntity(OrderBaseNodeFieldDO dO) {
        return new OrderBaseNodeField(dO);
    }

    @Override
    public OrderBaseNodeField toEntity(OrderBaseNodeFieldDTO dto) {
        return new OrderBaseNodeField(toDo(dto));
    }

    @Override
    protected Class<OrderBaseNodeFieldDO> getDoClass() {
        return OrderBaseNodeFieldDO.class;
    }

    @Override
    protected Class<OrderBaseNodeFieldDTO> getDtoClass() {
        return OrderBaseNodeFieldDTO.class;
    }
}

