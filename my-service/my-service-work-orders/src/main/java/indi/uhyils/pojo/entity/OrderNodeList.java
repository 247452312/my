package indi.uhyils.pojo.entity;

import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.repository.OrderNodeRouteRepository;
import indi.uhyils.util.AssertUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月04日 11时57分
 */
public class OrderNodeList extends AbstractEntity {


    private final List<OrderNode> orderNodes;

    public OrderNodeList(List<OrderNode> orderNodes) {
        this.orderNodes = orderNodes;
    }

    public void compareAndSaveDealUser(Map<Long, Long> dealUserIds) {
        AssertUtil.assertTrue(dealUserIds != null, "处理人不能为空");
        for (OrderNode orderNode : orderNodes) {
            Long id = orderNode.id.getId();
            Long dealUser = dealUserIds.get(id);
            if (dealUser == null) {
                continue;
            }
            orderNode.toDo().setRunDealUserId(dealUser);
            orderNode.onUpdate();
        }
    }

    public void compareAndSaveNoticeUser(Map<Long, Long> noticeUserIds) {
        AssertUtil.assertTrue(noticeUserIds != null, "通知人不能为空");
        for (OrderNode orderNode : orderNodes) {
            Long id = orderNode.id.getId();
            Long noticeUser = noticeUserIds.get(id);
            if (noticeUser == null) {
                continue;
            }
            orderNode.toDo().setNoticeUserId(noticeUser);
            orderNode.onUpdate();
        }

    }

    public void saveAll(OrderNodeRepository nodeRepository) {
        nodeRepository.save(orderNodes);
    }

    public void delFields(OrderNodeFieldRepository fieldRepository) {
        List<Long> collect = orderNodes.stream().map(t -> t.id.getId()).collect(Collectors.toList());
        List<OrderNodeField> byNodeIds = fieldRepository.findByNodeIds(collect);
        List<OrderNodeField> fields = byNodeIds.stream().peek(t -> t.data.setDeleteFlag(true)).peek(AbstractEntity::onUpdate).collect(Collectors.toList());
        fieldRepository.save(fields);
    }

    public void delRoutes(OrderNodeRouteRepository routeRepository) {
        List<Long> collect = orderNodes.stream().map(t -> t.id.getId()).collect(Collectors.toList());
        List<OrderNodeRoute> routes = routeRepository.findByNodeIds(collect);
        List<OrderNodeRoute> route = routes.stream().peek(t -> t.data.setDeleteFlag(true)).peek(AbstractEntity::onUpdate).collect(Collectors.toList());
        routeRepository.save(route);
    }

    public void delResultType(OrderNodeResultTypeRepository resultTypeRepository) {
        List<Long> collect = orderNodes.stream().map(t -> t.id.getId()).collect(Collectors.toList());
        List<OrderNodeResultType> routes = resultTypeRepository.findByNodeIds(collect);
        List<OrderNodeResultType> route = routes.stream().peek(t -> t.data.setDeleteFlag(true)).peek(AbstractEntity::onUpdate).collect(Collectors.toList());
        resultTypeRepository.save(route);
    }

    public void delSelf(OrderNodeRepository rep) {
        rep.remove(id);
    }
}