package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.DTO.OrderNodeResultTypeDTO;
import indi.uhyils.pojo.entity.OrderNodeResultType;

/**
 * 工单节点处理结果样例表(OrderNodeResultType)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分30秒
 */
@Assembler
public class OrderNodeResultTypeAssembler extends AbstractAssembler<OrderNodeResultTypeDO, OrderNodeResultType, OrderNodeResultTypeDTO> {

    @Override
    public OrderNodeResultType toEntity(OrderNodeResultTypeDO dO) {
        return new OrderNodeResultType(dO);
    }

    @Override
    public OrderNodeResultType toEntity(OrderNodeResultTypeDTO dto) {
        return new OrderNodeResultType(toDo(dto));
    }

    @Override
    protected Class<OrderNodeResultTypeDO> getDoClass() {
        return OrderNodeResultTypeDO.class;
    }

    @Override
    protected Class<OrderNodeResultTypeDTO> getDtoClass() {
        return OrderNodeResultTypeDTO.class;
    }
}

