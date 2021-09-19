package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.repository.OrderBaseNodeFieldRepository;
import indi.uhyils.repository.OrderBaseNodeRepository;
import indi.uhyils.repository.OrderBaseNodeResultTypeRepository;
import indi.uhyils.repository.OrderBaseNodeRouteRepository;
import indi.uhyils.util.CollectionUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 工单基础信息样例表(OrderBaseInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时58分53秒
 */
public class OrderBaseInfo extends AbstractDoEntity<OrderBaseInfoDO> {

    private List<OrderBaseNode> nodes;

    public OrderBaseInfo(OrderBaseInfoDO dO) {
        super(dO);
    }

    public OrderBaseInfo(Long id) {
        super(id, new OrderBaseInfoDO());
    }

    public List<OrderBaseNode> nodes() {
        return nodes;
    }

    /**
     * 填充节点
     *
     * @param nodeRepository
     */
    public void fillNoHiddenNode(OrderBaseNodeRepository nodeRepository) {
        if (nodes == null) {
            this.nodes = nodeRepository.findNoHiddenNodeById(id);
        }
    }

    public void recursionFillNodeInfo(OrderBaseNodeFieldRepository fieldRepository, OrderBaseNodeRouteRepository routeRepository, OrderBaseNodeResultTypeRepository resultTypeRepository) {
        if (CollectionUtil.isEmpty(nodes)) {
            return;
        }
        fillNodeField(fieldRepository);
        fillNodeResultType(resultTypeRepository);
        fillNodeRoute(routeRepository);
    }

    public void fillNodeRoute(OrderBaseNodeRouteRepository routeRepository) {
        List<OrderBaseNodeRoute> routes = routeRepository.findNodeRouteByNodes(this.nodes.stream().map(t -> t.id).collect(Collectors.toList()));
        Map<Long, List<OrderBaseNodeRoute>> nodeIdRouteMap = routes.stream().collect(Collectors.groupingBy(t -> t.toDo().getPrevNodeId()));
        for (OrderBaseNode node : this.nodes) {
            Long id = node.id.getId();
            List<OrderBaseNodeRoute> orderBaseNodeRoutes = nodeIdRouteMap.get(id);
            node.fillRoutes(orderBaseNodeRoutes);
        }

    }

    public void fillNodeResultType(OrderBaseNodeResultTypeRepository resultTypeRepository) {
        List<OrderBaseNodeResultType> resultTypes = resultTypeRepository.findNodeResultTypeByNodes(this.nodes.stream().map(t -> t.id).collect(Collectors.toList()));
        Map<Long, List<OrderBaseNodeResultType>> nodeIdResultTypeMap = resultTypes.stream().collect(Collectors.groupingBy(t -> t.toDo().getBaseNodeId()));
        for (OrderBaseNode node : this.nodes) {
            Long id = node.id.getId();
            List<OrderBaseNodeResultType> orderBaseNodeResultTypes = nodeIdResultTypeMap.get(id);
            node.fillResultTypes(orderBaseNodeResultTypes);
        }
    }

    public void fillNodeField(OrderBaseNodeFieldRepository fieldRepository) {
        List<OrderBaseNodeField> fields = fieldRepository.findNodeFieldByNodes(this.nodes.stream().map(t -> t.id).collect(Collectors.toList()));
        Map<Long, List<OrderBaseNodeField>> nodeIdFieldMap = fields.stream().collect(Collectors.groupingBy(t -> t.toDo().getBaseOrderId()));
        for (OrderBaseNode node : this.nodes) {
            Long id = node.id.getId();
            List<OrderBaseNodeField> fieldList = nodeIdFieldMap.get(id);
            node.fillFields(fieldList);
        }
    }

    public void forceFillNode(List<OrderBaseNode> nodes) {
        this.nodes = nodes;
    }
}
