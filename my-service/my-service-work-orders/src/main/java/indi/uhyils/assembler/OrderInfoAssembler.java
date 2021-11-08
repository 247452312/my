package indi.uhyils.assembler;


import indi.uhyils.enum_.OrderNodeTypeEnum;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DTO.InitOrderDTO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.DTO.OrderNodeDTO;
import indi.uhyils.pojo.DTO.OrderNodeFieldDTO;
import indi.uhyils.pojo.entity.OrderInfo;
import indi.uhyils.pojo.entity.OrderNode;
import java.util.HashMap;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单基础信息样例表(OrderInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分13秒
 */
@Mapper(componentModel = "spring")
public abstract class OrderInfoAssembler extends AbstractAssembler<OrderInfoDO, OrderInfo, OrderInfoDTO> {

    @Autowired
    private OrderNodeAssembler nodeAssembler;

    @Autowired
    private OrderNodeFieldAssembler nodeFieldAssembler;


    @Override
    public OrderInfo toEntity(OrderInfoDTO dto) {
        OrderInfo orderInfo = new OrderInfo(toDo(dto));
        List<OrderNodeDTO> nodes = dto.getNodes();
        List<OrderNode> orderNodes = nodeAssembler.listDTOToEntity(nodes);
        orderInfo.forceFillNodes(orderNodes);
        return orderInfo;
    }

    public abstract OrderInfoDTO baseInfoDTOToInfoDTO(OrderBaseInfoDTO order);

    public InitOrderDTO toInitOrderDTO(OrderInfo orderInfo) {
        OrderInfoDO orderInfoDO = orderInfo.toData();
        List<OrderNode> nodes = orderInfo.nodes();

        // 创建之后的首节点的属性(返回给前台用)
        List<OrderNodeFieldDTO> orderNodeField = null;
        // 每个节点的处理人(返回给前台用)
        HashMap<Long, Long> dealUserIds = new HashMap<>(nodes.size());
        // 每个节点的抄送人(返回给前台用)
        HashMap<Long, Long> noticeUserIds = new HashMap<>(nodes.size());

        for (OrderNode node : nodes) {
            OrderNodeDO orderNodeDO = node.toData();
            Integer type = orderNodeDO.getType();
            OrderNodeTypeEnum orderNodeType = OrderNodeTypeEnum.parse(type);
            if (orderNodeType == OrderNodeTypeEnum.START) {
                orderNodeField = nodeFieldAssembler.listEntityToDTO(node.fields());
            }

            dealUserIds.put(orderNodeDO.getId(), orderNodeDO.getRunDealUserId());
            noticeUserIds.put(orderNodeDO.getId(), orderNodeDO.getNoticeUserId());
        }

        return InitOrderDTO.build(orderInfo.getUnique().getId(), orderNodeField, orderInfoDO.getMonitorUserId(), dealUserIds, noticeUserIds);
    }
}

