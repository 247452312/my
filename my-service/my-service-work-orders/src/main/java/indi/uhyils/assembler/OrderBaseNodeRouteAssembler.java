package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.OrderBaseNodeRouteDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeRouteDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeRoute;
import org.mapstruct.Mapper;

/**
 * 节点间关联路由样例表(OrderBaseNodeRoute)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分07秒
 */
@Mapper(componentModel = "spring")
public abstract class OrderBaseNodeRouteAssembler extends AbstractAssembler<OrderBaseNodeRouteDO, OrderBaseNodeRoute, OrderBaseNodeRouteDTO> {

}
