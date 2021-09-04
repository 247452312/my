package indi.uhyils.pojo.entity;

import indi.uhyils.assembler.OrderInfoAssembler;
import indi.uhyils.enum_.OrderNodeStatusEnum;
import indi.uhyils.enum_.OrderStatusEnum;
import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.facade.PushFacade;
import indi.uhyils.pojo.DO.OrderApplyDO;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.OrderApplyRepository;
import indi.uhyils.repository.OrderInfoRepository;
import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.repository.OrderNodeRepository;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.repository.OrderNodeRouteRepository;
import indi.uhyils.util.AssertUtil;
import java.util.List;

/**
 * (OrderApply)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时58分49秒
 */
public class OrderApply extends AbstractDoEntity<OrderApplyDO> {

    /**
     * 这个申请的节点
     */
    private OrderNode orderNode;

    public OrderApply(OrderApplyDO dO) {
        super(dO);
    }

    public OrderApply(Long id) {
        super(id, new OrderApplyDO());
    }

    public void completionThisNode(OrderNodeRepository nodeRepository, OrderNodeFieldRepository fieldRepository, OrderNodeRouteRepository routeRepository, OrderNodeResultTypeRepository resultTypeRepository) {
        if (this.orderNode != null) {
            return;
        }

        this.orderNode = nodeRepository.find(new Identifier(toDo().getOrderNodeId()));
        List<OrderNodeField> fields = fieldRepository.findByNodeId(orderNode.data.getId());
        List<OrderNodeResultType> resultTypes = resultTypeRepository.findByNodeId(orderNode.data.getId());
        List<OrderNodeRoute> route = routeRepository.findByNodeId(orderNode.data.getId());
        this.orderNode.forceFillInfo(fields, resultTypes, route);

    }

    public void changeThisNodeStatus(OrderNodeRepository orderNodeRepository, OrderNodeStatusEnum status) {
        AssertUtil.assertTrue(orderNode != null, "节点没有初始化");
        orderNode.changeStatus(orderNodeRepository, status);

    }

    public OrderNode copyToLastNode() {
        OrderNode copy = orderNode.copy();
        OrderNodeDO orderNodeDO = copy.toDo();
        orderNodeDO.setStatus(OrderStatusEnum.STOP.getCode());
        orderNodeDO.setName(orderNodeDO.getName() + "(转交)");
        orderNodeDO.setRunDealUserId(data.getTargetUserId());
        orderNodeDO.preInsert();
        return copy;

    }

    public OrderNode node() {
        return orderNode;
    }

    public void noticeTargetUser(OrderInfoRepository rep, OrderInfoAssembler assem, PushFacade pushFacade, PushTypeEnum email) {
        noticeTargetUser(rep, assem, OrderStatusEnum.CIRCULATION, pushFacade, email);
    }

    public void noticeTargetUser(OrderInfoRepository rep, OrderInfoAssembler assem, OrderStatusEnum status, PushFacade pushFacade, PushTypeEnum email) {
        OrderInfo orderInfo = rep.find(new Identifier(data.getOrderId()));
        pushFacade.pushMsg(assem.toDTO(orderInfo), data.getTargetUserId(), status, email);
    }

    public void saveSelf(OrderApplyRepository rep) {
        rep.save(this);
    }
}
