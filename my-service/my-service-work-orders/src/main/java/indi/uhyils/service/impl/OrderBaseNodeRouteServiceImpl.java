package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderBaseNodeRouteAssembler;
import indi.uhyils.pojo.DO.OrderBaseNodeRouteDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeRouteDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeRoute;
import indi.uhyils.repository.OrderBaseNodeRouteRepository;
import indi.uhyils.service.OrderBaseNodeRouteService;
import org.springframework.stereotype.Service;

/**
 * 节点间关联路由样例表(OrderBaseNodeRoute)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分08秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_base_node_route"})
public class OrderBaseNodeRouteServiceImpl extends AbstractDoService<OrderBaseNodeRouteDO, OrderBaseNodeRoute, OrderBaseNodeRouteDTO, OrderBaseNodeRouteRepository, OrderBaseNodeRouteAssembler> implements OrderBaseNodeRouteService {

    public OrderBaseNodeRouteServiceImpl(OrderBaseNodeRouteAssembler assembler, OrderBaseNodeRouteRepository repository) {
        super(assembler, repository);
    }


}
