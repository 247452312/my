package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DTO.OrderNodeDTO;
import indi.uhyils.pojo.entity.OrderNode;

/**
 * 工单节点样例表(OrderNode)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分20秒
 */
@Assembler
public class OrderNodeAssembler extends AbstractAssembler<OrderNodeDO, OrderNode, OrderNodeDTO> {

    @Override
    public OrderNode toEntity(OrderNodeDO dO) {
        return new OrderNode(dO);
    }

    @Override
    public OrderNode toEntity(OrderNodeDTO dto) {
        return new OrderNode(toDo(dto));
    }

    @Override
    protected Class<OrderNodeDO> getDoClass() {
        return OrderNodeDO.class;
    }

    @Override
    protected Class<OrderNodeDTO> getDtoClass() {
        return OrderNodeDTO.class;
    }
}

