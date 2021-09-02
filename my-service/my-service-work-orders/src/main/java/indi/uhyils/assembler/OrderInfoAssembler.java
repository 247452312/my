package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.DTO.OrderNodeDTO;
import indi.uhyils.pojo.entity.OrderInfo;
import indi.uhyils.pojo.entity.OrderNode;
import indi.uhyils.util.BeanUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单基础信息样例表(OrderInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分13秒
 */
@Assembler
public class OrderInfoAssembler extends AbstractAssembler<OrderInfoDO, OrderInfo, OrderInfoDTO> {

    @Autowired
    private OrderNodeAssembler nodeAssembler;

    @Override
    public OrderInfo toEntity(OrderInfoDO dO) {
        return new OrderInfo(dO);
    }

    @Override
    public OrderInfo toEntity(OrderInfoDTO dto) {
        OrderInfo orderInfo = new OrderInfo(toDo(dto));
        List<OrderNodeDTO> nodes = dto.getNodes();
        List<OrderNode> orderNodes = nodeAssembler.listDTOToEntity(nodes);
        orderInfo.forceFillNodes(orderNodes);
        return orderInfo;
    }

    @Override
    protected Class<OrderInfoDO> getDoClass() {
        return OrderInfoDO.class;
    }

    @Override
    protected Class<OrderInfoDTO> getDtoClass() {
        return OrderInfoDTO.class;
    }

    public OrderInfoDTO baseInfoDTOToInfoDTO(OrderBaseInfoDTO order) {
        return BeanUtil.copyProperties(order, OrderInfoDTO.class);
    }

}

