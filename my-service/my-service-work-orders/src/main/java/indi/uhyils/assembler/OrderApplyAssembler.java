package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderApplyDO;
import indi.uhyils.pojo.DTO.OrderApplyDTO;
import indi.uhyils.pojo.entity.OrderApply;

/**
 * (OrderApply)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分49秒
 */
@Assembler
public class OrderApplyAssembler extends AbstractAssembler<OrderApplyDO, OrderApply, OrderApplyDTO> {

    @Override
    public OrderApply toEntity(OrderApplyDO dO) {
        return new OrderApply(dO);
    }

    @Override
    public OrderApply toEntity(OrderApplyDTO dto) {
        return new OrderApply(toDo(dto));
    }

    @Override
    protected Class<OrderApplyDO> getDoClass() {
        return OrderApplyDO.class;
    }

    @Override
    protected Class<OrderApplyDTO> getDtoClass() {
        return OrderApplyDTO.class;
    }
}

