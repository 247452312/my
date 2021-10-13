package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeDTO;
import indi.uhyils.pojo.entity.OrderBaseNode;
import indi.uhyils.pojo.entity.OrderBaseNodeField;
import indi.uhyils.pojo.entity.OrderBaseNodeResultType;
import indi.uhyils.pojo.entity.OrderBaseNodeRoute;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单节点样例表(OrderBaseNode)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分57秒
 */
@Mapper(componentModel = "spring")
public abstract class OrderBaseNodeAssembler extends AbstractAssembler<OrderBaseNodeDO, OrderBaseNode, OrderBaseNodeDTO> {

    @Autowired
    private OrderBaseNodeFieldAssembler fieldAssembler;

    @Autowired
    private OrderBaseNodeResultTypeAssembler resultTypeAssembler;

    @Autowired
    private OrderBaseNodeRouteAssembler routeAssembler;


    @Override
    public OrderBaseNode toEntity(OrderBaseNodeDTO dto) {
        OrderBaseNode orderBaseNode = new OrderBaseNode(toDo(dto));
        List<OrderBaseNodeField> fields = fieldAssembler.listDTOToEntity(dto.getFields());
        orderBaseNode.forceFillFields(fields);
        List<OrderBaseNodeRoute> routes = routeAssembler.listDTOToEntity(dto.getRoutes());
        orderBaseNode.forceFillRoutes(routes);
        List<OrderBaseNodeResultType> resultTypes = resultTypeAssembler.listDTOToEntity(dto.getResultTypes());
        orderBaseNode.forceFillResultTypes(resultTypes);
        return orderBaseNode;
    }

    @Override
    public OrderBaseNodeDTO toDTO(OrderBaseNode entity) {
        OrderBaseNodeDTO orderBaseNodeDTO = toDTO(entity.toData());
        orderBaseNodeDTO.setFields(fieldAssembler.listEntityToDTO(entity.fields()));
        orderBaseNodeDTO.setRoutes(routeAssembler.listEntityToDTO(entity.routes()));
        orderBaseNodeDTO.setResultTypes(resultTypeAssembler.listEntityToDTO(entity.resultTypes()));
        return orderBaseNodeDTO;
    }
}

