package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.entity.OrderBaseInfo;

/**
 * 工单基础信息样例表(OrderBaseInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分53秒
 */
@Assembler
public class OrderBaseInfoAssembler extends AbstractAssembler<OrderBaseInfoDO, OrderBaseInfo, OrderBaseInfoDTO> {

    @Override
    public OrderBaseInfo toEntity(OrderBaseInfoDO dO) {
        return new OrderBaseInfo(dO);
    }

    @Override
    public OrderBaseInfo toEntity(OrderBaseInfoDTO dto) {
        return new OrderBaseInfo(toDo(dto));
    }

    @Override
    protected Class<OrderBaseInfoDO> getDoClass() {
        return OrderBaseInfoDO.class;
    }

    @Override
    protected Class<OrderBaseInfoDTO> getDtoClass() {
        return OrderBaseInfoDTO.class;
    }
}

