package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderBaseNodeAssembler;
import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeDTO;
import indi.uhyils.pojo.entity.OrderBaseNode;
import indi.uhyils.repository.OrderBaseNodeRepository;
import indi.uhyils.service.OrderBaseNodeService;
import org.springframework.stereotype.Service;

/**
 * 工单节点样例表(OrderBaseNode)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分58秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_base_node"})
public class OrderBaseNodeServiceImpl extends AbstractDoService<OrderBaseNodeDO, OrderBaseNode, OrderBaseNodeDTO, OrderBaseNodeRepository, OrderBaseNodeAssembler> implements OrderBaseNodeService {

    public OrderBaseNodeServiceImpl(OrderBaseNodeAssembler assembler, OrderBaseNodeRepository repository) {
        super(assembler, repository);
    }


}
