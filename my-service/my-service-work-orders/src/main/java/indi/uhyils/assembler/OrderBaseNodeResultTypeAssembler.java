package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderBaseNodeResultTypeDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeResultTypeDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeResultType;

/**
 * 工单节点处理结果样例表(OrderBaseNodeResultType)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分04秒
 */
@Assembler
public class OrderBaseNodeResultTypeAssembler extends AbstractAssembler<OrderBaseNodeResultTypeDO, OrderBaseNodeResultType, OrderBaseNodeResultTypeDTO> {

    @Override
    public OrderBaseNodeResultType toEntity(OrderBaseNodeResultTypeDO dO) {
        return new OrderBaseNodeResultType(dO);
    }

    @Override
    public OrderBaseNodeResultType toEntity(OrderBaseNodeResultTypeDTO dto) {
        return new OrderBaseNodeResultType(toDo(dto));
    }

    @Override
    protected Class<OrderBaseNodeResultTypeDO> getDoClass() {
        return OrderBaseNodeResultTypeDO.class;
    }

    @Override
    protected Class<OrderBaseNodeResultTypeDTO> getDtoClass() {
        return OrderBaseNodeResultTypeDTO.class;
    }
}

