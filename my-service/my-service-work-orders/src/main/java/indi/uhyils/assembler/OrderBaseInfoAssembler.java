package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.DTO.OrderBaseNodeDTO;
import indi.uhyils.pojo.entity.OrderBaseInfo;
import indi.uhyils.pojo.entity.OrderBaseNode;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单基础信息样例表(OrderBaseInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分53秒
 */
@Mapper(componentModel = "spring")
public abstract class OrderBaseInfoAssembler extends AbstractAssembler<OrderBaseInfoDO, OrderBaseInfo, OrderBaseInfoDTO> {

    @Autowired
    private OrderBaseNodeAssembler nodeAssembler;

    @Override
    public OrderBaseInfo toEntity(OrderBaseInfoDO dO) {
        return new OrderBaseInfo(dO);
    }

    @Override
    public OrderBaseInfo toEntity(OrderBaseInfoDTO dto) {
        OrderBaseInfo orderBaseInfo = new OrderBaseInfo(toDo(dto));
        List<OrderBaseNodeDTO> nodes = dto.getNodes();
        List<OrderBaseNode> orderBaseNodes = nodeAssembler.listDTOToEntity(nodes);
        orderBaseInfo.forceFillNode(orderBaseNodes);
        return orderBaseInfo;
    }

    @Override
    public OrderBaseInfoDTO toDTO(OrderBaseInfo entity) {
        OrderBaseInfoDTO orderBaseInfoDTO = toDTO(entity.toData());
        List<OrderBaseNode> nodes = entity.nodes();
        List<OrderBaseNodeDTO> orderBaseNodeDTOList = nodeAssembler.listEntityToDTO(nodes);
        orderBaseInfoDTO.setNodes(orderBaseNodeDTOList);
        return orderBaseInfoDTO;
    }
}

