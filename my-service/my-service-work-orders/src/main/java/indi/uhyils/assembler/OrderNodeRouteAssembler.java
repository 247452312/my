package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderNodeRouteDO;
import indi.uhyils.pojo.DTO.OrderNodeRouteDTO;
import indi.uhyils.pojo.entity.OrderNodeRoute;

/**
 * 节点间关联路由样例表(OrderNodeRoute)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分33秒
 */
@Assembler
public class OrderNodeRouteAssembler extends AbstractAssembler<OrderNodeRouteDO, OrderNodeRoute, OrderNodeRouteDTO> {

    @Override
    public OrderNodeRoute toEntity(OrderNodeRouteDO dO) {
        return new OrderNodeRoute(dO);
    }

    @Override
    public OrderNodeRoute toEntity(OrderNodeRouteDTO dto) {
        return new OrderNodeRoute(toDo(dto));
    }

    @Override
    protected Class<OrderNodeRouteDO> getDoClass() {
        return OrderNodeRouteDO.class;
    }

    @Override
    protected Class<OrderNodeRouteDTO> getDtoClass() {
        return OrderNodeRouteDTO.class;
    }
}

