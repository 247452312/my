package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.IdMapping;
import indi.uhyils.repository.OrderInfoRepository;
import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.repository.OrderNodeRouteRepository;
import indi.uhyils.util.CollectionUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工单基础信息样例表(OrderInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分13秒
 */
public class OrderInfo extends AbstractDoEntity<OrderInfoDO> {

    private List<OrderNode> nodes;


    public OrderInfo(OrderInfoDO dO) {
        super(dO);
    }

    public OrderInfo(Long id) {
        super(id, new OrderInfoDO());
    }

    public void forceFillNodes(List<OrderNode> nodes) {
        this.nodes = nodes;
    }

    public void saveSelf(OrderInfoRepository rep) {
        toDo().setId(null);
        this.id = null;
        this.id = rep.save(this);
        // 修改node的OrderId
        changeNodesOrderId();
    }

    private void changeNodesOrderId() {
        if (CollectionUtil.isEmpty(nodes)) {
            return;
        }
        for (OrderNode node : nodes) {
            node.toDo().setBaseInfoId(id.getId());
        }
    }

    public void saveNode(OrderNodeRepository nodeRepository, OrderNodeFieldRepository fieldRepository, OrderNodeRouteRepository routeRepository, OrderNodeResultTypeRepository resultTypeRepository) {
        if (CollectionUtil.isEmpty(nodes)) {
            return;
        }
        Map<Long, Long> idMappings = new HashMap<>(this.nodes.size());
        for (OrderNode node : this.nodes) {
            IdMapping idMapping = node.saveSelf(nodeRepository, fieldRepository, routeRepository, resultTypeRepository);
            idMappings.put(idMapping.getOldId(), idMapping.getNewId());
        }

        for (OrderNode node : nodes) {
            node.changeAndSaveFieldNodeId(idMappings, fieldRepository);
            node.changeAndSaveResultTypeAndRoute(idMappings, routeRepository, resultTypeRepository);
        }

    }
}
