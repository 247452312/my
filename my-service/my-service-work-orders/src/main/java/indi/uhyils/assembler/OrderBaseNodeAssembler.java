package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeDTO;
import indi.uhyils.pojo.entity.OrderBaseNode;

/**
 * 工单节点样例表(OrderBaseNode)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分57秒
 */
@Assembler
public class OrderBaseNodeAssembler extends AbstractAssembler<OrderBaseNodeDO, OrderBaseNode, OrderBaseNodeDTO> {

    @Override
    public OrderBaseNode toEntity(OrderBaseNodeDO dO) {
        return new OrderBaseNode(dO);
    }

    @Override
    public OrderBaseNode toEntity(OrderBaseNodeDTO dto) {
        return new OrderBaseNode(toDo(dto));
    }

    @Override
    protected Class<OrderBaseNodeDO> getDoClass() {
        return OrderBaseNodeDO.class;
    }

    @Override
    protected Class<OrderBaseNodeDTO> getDtoClass() {
        return OrderBaseNodeDTO.class;
    }
}

