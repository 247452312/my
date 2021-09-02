package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.DO.OrderNodeRouteDO;
import indi.uhyils.pojo.IdMapping;
import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.repository.OrderNodeRouteRepository;
import indi.uhyils.util.CollectionUtil;
import java.util.List;
import java.util.Map;

/**
 * 工单节点样例表(OrderNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时59分20秒
 */
public class OrderNode extends AbstractDoEntity<OrderNodeDO> {

    /**
     * 节点属性
     */
    private List<OrderNodeField> fields;

    /**
     * 节点结果类型
     */
    private List<OrderNodeResultType> resultTypes;

    /**
     * 节点结果路由
     */
    private List<OrderNodeRoute> routes;

    public OrderNode(OrderNodeDO dO) {
        super(dO);
    }

    public OrderNode(Long id) {
        super(id, new OrderNodeDO());
    }

    public void forceFillInfo(List<OrderNodeField> fields, List<OrderNodeResultType> resultTypes, List<OrderNodeRoute> routes) {
        this.fields = fields;
        this.resultTypes = resultTypes;
        this.routes = routes;
    }

    /**
     * 保存自身
     *
     * @param nodeRepository       节点
     * @param fieldRepository      属性
     * @param routeRepository      路由
     * @param resultTypeRepository 结果类型
     */
    public IdMapping saveSelf(
        OrderNodeRepository nodeRepository, OrderNodeFieldRepository fieldRepository, OrderNodeRouteRepository routeRepository, OrderNodeResultTypeRepository resultTypeRepository) {
        Long oldId = this.id.getId();
        this.id = null;
        toDo().setId(null);
        nodeRepository.save(this);
        Long newId = this.id.getId();
        return IdMapping.build(newId, oldId);
    }

    public void changeAndSaveFieldNodeId(Map<Long, Long> idMappings, OrderNodeFieldRepository fieldRepository) {
        if (CollectionUtil.isEmpty(fields)) {
            return;
        }
        for (OrderNodeField field : fields) {
            OrderNodeFieldDO orderNodeFieldDO = field.toDo();
            orderNodeFieldDO.setBaseOrderNodeId(idMappings.get(orderNodeFieldDO.getBaseOrderNodeId()));
            field.id = null;
            orderNodeFieldDO.setId(null);
            field.id = fieldRepository.save(field);
        }
    }

    public void changeAndSaveResultTypeAndRoute(Map<Long, Long> idMappings, OrderNodeRouteRepository routeRepository, OrderNodeResultTypeRepository resultTypeRepository) {
        if (CollectionUtil.isEmpty(resultTypes) || CollectionUtil.isEmpty(routes)) {
            return;
        }
        for (OrderNodeResultType resultType : resultTypes) {
            Long oldId = resultType.id.getId();
            OrderNodeResultTypeDO orderNodeResultTypeDO = resultType.toDo();
            orderNodeResultTypeDO.setBaseNodeId(idMappings.get(orderNodeResultTypeDO.getBaseNodeId()));
            orderNodeResultTypeDO.setId(null);
            resultType.id = null;
            resultType.id = resultTypeRepository.save(resultType);
            Long newId = resultType.id.getId();
            idMappings.put(oldId, newId);
        }

        for (OrderNodeRoute route : routes) {
            OrderNodeRouteDO orderNodeRouteDO = route.toDo();
            orderNodeRouteDO.setNextNodeId(idMappings.get(orderNodeRouteDO.getNextNodeId()));
            orderNodeRouteDO.setPrevNodeId(idMappings.get(orderNodeRouteDO.getPrevNodeId()));
            orderNodeRouteDO.setResultId(idMappings.get(orderNodeRouteDO.getResultId()));
            orderNodeRouteDO.setId(null);
            route.id = null;
            route.id = routeRepository.save(route);
        }
    }
}
