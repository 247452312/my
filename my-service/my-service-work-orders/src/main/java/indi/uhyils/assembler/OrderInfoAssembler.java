package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.entity.OrderInfo;

/**
 * 工单基础信息样例表(OrderInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分13秒
 */
@Assembler
public class OrderInfoAssembler extends AbstractAssembler<OrderInfoDO, OrderInfo, OrderInfoDTO> {

    @Override
    public OrderInfo toEntity(OrderInfoDO dO) {
        return new OrderInfo(dO);
    }

    @Override
    public OrderInfo toEntity(OrderInfoDTO dto) {
        return new OrderInfo(toDo(dto));
    }

    @Override
    protected Class<OrderInfoDO> getDoClass() {
        return OrderInfoDO.class;
    }

    @Override
    protected Class<OrderInfoDTO> getDtoClass() {
        return OrderInfoDTO.class;
    }
}

