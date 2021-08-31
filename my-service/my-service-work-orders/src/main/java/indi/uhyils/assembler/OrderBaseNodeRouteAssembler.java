package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderBaseNodeRouteDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeRouteDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeRoute;

/**
 * 节点间关联路由样例表(OrderBaseNodeRoute)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分07秒
 */
@Assembler
public class OrderBaseNodeRouteAssembler extends AbstractAssembler<OrderBaseNodeRouteDO, OrderBaseNodeRoute, OrderBaseNodeRouteDTO> {

    @Override
    public OrderBaseNodeRoute toEntity(OrderBaseNodeRouteDO dO) {
        return new OrderBaseNodeRoute(dO);
    }

    @Override
    public OrderBaseNodeRoute toEntity(OrderBaseNodeRouteDTO dto) {
        return new OrderBaseNodeRoute(toDo(dto));
    }

    @Override
    protected Class<OrderBaseNodeRouteDO> getDoClass() {
        return OrderBaseNodeRouteDO.class;
    }

    @Override
    protected Class<OrderBaseNodeRouteDTO> getDtoClass() {
        return OrderBaseNodeRouteDTO.class;
    }
}

