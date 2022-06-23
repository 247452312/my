package indi.uhyils.assembler;


import indi.uhyils.builder.OrderApplyBuilder;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DTO.OrderApplyDTO;
import indi.uhyils.pojo.DTO.OrderNodeDTO;
import indi.uhyils.pojo.DTO.OrderNodeFieldDTO;
import indi.uhyils.pojo.DTO.OrderNodeResultTypeDTO;
import indi.uhyils.pojo.DTO.OrderNodeRouteDTO;
import indi.uhyils.pojo.entity.OrderApply;
import indi.uhyils.pojo.entity.OrderInfo;
import indi.uhyils.pojo.entity.OrderNode;
import indi.uhyils.util.Asserts;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单节点样例表(OrderNode)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分20秒
 */
@Mapper(componentModel = "spring")
public abstract class OrderNodeAssembler extends AbstractAssembler<OrderNodeDO, OrderNode, OrderNodeDTO> {

    @Autowired
    private OrderNodeFieldAssembler fieldAssembler;

    @Autowired
    private OrderNodeRouteAssembler routeAssembler;

    @Autowired
    private OrderNodeResultTypeAssembler resultTypeAssembler;

    @Autowired
    private OrderApplyAssembler applyAssembler;

    @Override
    public OrderNode toEntity(OrderNodeDTO dto) {
        OrderNode orderNode = new OrderNode(toDo(dto));
        List<OrderNodeFieldDTO> fields = dto.getFields();
        List<OrderNodeResultTypeDTO> resultTypes = dto.getResultTypes();
        List<OrderNodeRouteDTO> routes = dto.getRoutes();
        orderNode.forceFillInfo(fieldAssembler.listDTOToEntity(fields), resultTypeAssembler.listDTOToEntity(resultTypes), routeAssembler.listDTOToEntity(routes));
        return orderNode;
    }

    public OrderApply toApply(OrderNode orderNode, OrderInfo baseInfo) {
        OrderApplyDTO orderApplyDTO = OrderApplyBuilder.buildTransApplyByOrderNode(toDTO(orderNode), baseInfo.toData().map(OrderInfoDO::getMonitorUserId).orElseThrow(Asserts::throwOptionalException));
        return applyAssembler.toEntity(orderApplyDTO);
    }
}

