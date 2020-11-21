package indi.uhyils.serviceImpl;

import indi.uhyils.builder.OrderNodeFieldValueBuilder;
import indi.uhyils.dao.*;
import indi.uhyils.enum_.*;
import indi.uhyils.pojo.model.*;
import indi.uhyils.pojo.request.*;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.InsertOrderResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.OrderService;
import indi.uhyils.util.DubboApiUtil;
import indi.uhyils.util.OrderBuilder;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时34分
 */
@Service(group = "${spring.profiles.active}")
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderBaseInfoDao orderBaseInfoDao;

    @Resource
    private OrderBaseNodeDao orderBaseNodeDao;

    @Resource
    private OrderBaseNodeFieldDao orderBaseNodeFieldDao;

    @Resource
    private OrderBaseNodeResultTypeDao orderBaseNodeResultTypeDao;

    @Resource
    private OrderBaseNodeRouteDao orderBaseNodeRouteDao;

    @Resource
    private OrderNodeDao orderNodeDao;
    @Resource
    private OrderNodeFieldDao orderNodeFieldDao;

    @Resource
    private OrderNodeResultTypeDao orderNodeResultTypeDao;

    @Resource
    private OrderNodeRouteDao orderNodeRouteDao;


    @Resource
    private OrderInfoDao orderInfoDao;

    @Resource
    private OrderNodeFieldValueDao orderNodeFieldValueDao;

    @Override
    public ServiceResult<InsertOrderResponse> insertOrder(IdRequest request) {
        //插入order基础信息
        String baseInfoId = request.getId();
        OrderBaseInfoEntity baseInfo = orderBaseInfoDao.getById(baseInfoId);
        OrderInfoEntity orderInfoEntity = OrderBuilder.transBaseInfo2Info(baseInfo);
        orderInfoEntity.preInsert(request);
        orderInfoDao.insert(orderInfoEntity);

        // 获取基础表对应的所有节点
        List<OrderBaseNodeEntity> nodeList = orderBaseNodeDao.getByOrderId(baseInfoId);

        String infoId = orderInfoEntity.getId();
        // 保存所有的路由, 路由比较特殊 需要改node的id
        List<OrderNodeRouteEntity> allRouteEntities = new ArrayList<>();
        // 报存所有的新老node对应关系
        Map<String, String> oldToNew = new HashMap<>(nodeList.size());
        // 创建之后的首节点的属性(返回给前台用)
        ArrayList<OrderNodeFieldEntity> orderNodeField = new ArrayList<>();
        // 每个节点的处理人(返回给前台用)
        HashMap<String, String> dealUserIds = new HashMap<>(nodeList.size());
        // 每个节点的抄送人(返回给前台用)
        HashMap<String, String> noticeUserIds = new HashMap<>(nodeList.size());

        //获取基础节点对应的所有信息(属性,结果类型,路由) 并转换成实例信息插入实例表
        nodeList.forEach(node -> {

            Boolean isStartNode = NodeTypeEnum.START.getCode().equals(node.getType());

            //转换节点本身
            OrderNodeEntity orderNodeEntity = OrderBuilder.transBaseNode2Node(node, infoId);
            orderNodeEntity.preInsert(request);
            orderNodeDao.insert(orderNodeEntity);

            // 填充默认处理人与抄送人
            dealUserIds.put(orderNodeEntity.getId(), orderNodeEntity.getRunDealUserId());
            noticeUserIds.put(orderNodeEntity.getId(), orderNodeEntity.getNoticeUserId());

            oldToNew.put(node.getId(), orderNodeEntity.getId());
            //获取节点相关的所有东西(属性,结果类型,路由)
            List<OrderBaseNodeFieldEntity> baseNodeFieldEntity = orderBaseNodeFieldDao.getByOrderNodeId(node.getId());
            List<OrderBaseNodeResultTypeEntity> baseNodeResultTypeEntity = orderBaseNodeResultTypeDao.getByOrderNodeId(node.getId());
            List<OrderBaseNodeRouteEntity> baseRouteEntity = orderBaseNodeRouteDao.getByOrderNodeId(node.getId());

            String id = orderNodeEntity.getId();
            baseNodeFieldEntity.stream().map(field -> OrderBuilder.transBaseField2Field(field, id)).forEach(field -> {
                if (isStartNode) {
                    orderNodeField.add(field);
                }
                field.preInsert(request);
                orderNodeFieldDao.insert(field);
            });
            baseNodeResultTypeEntity.stream().map(resultType -> OrderBuilder.transBaseResultType2ResultType(resultType, id)).forEach(resultType -> {
                resultType.preInsert(request);
                orderNodeResultTypeDao.insert(resultType);
            });
            allRouteEntities.addAll(baseRouteEntity.stream().map(route -> OrderBuilder.transBaseRoute2Route(route, id)).collect(Collectors.toList()));
        });
        allRouteEntities.forEach(t -> {
            t.setPrevNodeId(oldToNew.get(t.getPrevNodeId()));
            t.setNextNodeId(oldToNew.get(t.getNextNodeId()));
            t.preInsert(request);
            orderNodeRouteDao.insert(t);
        });

        InsertOrderResponse build = InsertOrderResponse.build(infoId, orderNodeField, baseInfo.getMonitorUserId(), dealUserIds, noticeUserIds);
        return ServiceResult.buildSuccessResult("插入成功", build, request);
    }

    @Override
    public ServiceResult<Boolean> commitOrder(CommitOrderRequest request) {
        Map<String, String> value = request.getValue();
        List<OrderNodeFieldValueEntity> orderNodeFieldValueEntities = OrderNodeFieldValueBuilder.buildOrderNodeFieldValues(value);
        /*添加首节点的真实值*/
        orderNodeFieldValueEntities.forEach(t -> {
            t.preInsert(request);
            orderNodeFieldValueDao.insert(t);
        });
        /*更新主表的监管人*/
        OrderInfoEntity orderInfo = orderInfoDao.getById(request.getOrderId());
        String monitorUserId = orderInfo.getMonitorUserId();
        if (monitorUserId == null || !monitorUserId.equals(request.getMonitorUserId())) {
            orderInfo.setMonitorUserId(request.getMonitorUserId());
            orderInfo.preUpdate(request);
            orderInfoDao.update(orderInfo);
        }

        /*更新节点表的处理人,抄送人*/
        Map<String, String> dealUserIds = request.getDealUserIds();
        Map<String, String> noticeUserIds = request.getNoticeUserIds();
        Set<String> nodeIds = new HashSet<>();
        nodeIds.addAll(dealUserIds.keySet());
        nodeIds.addAll(noticeUserIds.keySet());
        List<OrderNodeEntity> orderNodeEntities = orderNodeDao.getByIds(nodeIds);
        for (OrderNodeEntity orderNodeEntity : orderNodeEntities) {
            boolean update = false;
            String orderDealUserId = dealUserIds.get(orderNodeEntity.getId());
            String noticeUserId = noticeUserIds.get(orderNodeEntity.getId());
            if (orderNodeEntity.getRunDealUserId() == null || !orderNodeEntity.getRunDealUserId().equals(orderDealUserId)) {
                orderNodeEntity.setRunDealUserId(orderDealUserId);
                orderNodeEntity.preUpdate(request);
                update = true;
            }
            if (orderNodeEntity.getNoticeUserId() == null || !orderNodeEntity.getNoticeUserId().equals(noticeUserId)) {
                orderNodeEntity.setNoticeUserId(noticeUserId);
                if (!update) {
                    orderNodeEntity.preUpdate(request);
                    update = true;
                }
            }
            if (update) {
                orderNodeDao.update(orderNodeEntity);
            }

        }

        return ServiceResult.buildSuccessResult("提交成功", true, request);
    }


    @Override
    public ServiceResult<Boolean> recallOrder(RecallOrderRequest request) {
        Boolean result = changeOrderStatus(request.getOrderId(), OrderStatusEnum.WITHDRAWING);
        if (result) {
            /*工单状态修改完成后通知工单监管人,进行审批处理,是否予以撤回,注意,此处返回值为是否发送申请成功*/
            boolean b = noticeMonitorUserIdAboutBackOrder(request);
            if (!b) {
                return ServiceResult.buildFailedResult("操作失败,推送系统异常", false, request);
            }
            return ServiceResult.buildSuccessResult("操作成功,等待审批人审批", true, request);
        }
        return ServiceResult.buildFailedResult("操作失败", false, request);
    }

    @Override
    public ServiceResult<Boolean> agreeRecallOrder(AgreeRecallOrderRequest request) {
        Boolean result = changeOrderStatus(request.getOrderId(), OrderStatusEnum.WITHDRAWED);
        return ServiceResult.buildSuccessResult("操作成功", result, request);
    }

    @Override
    public ServiceResult<Boolean> frozenOrder(FrozenOrderRequest request) {
        Boolean result = changeOrderStatus(request.getOrderId(), OrderStatusEnum.STOP);
        return ServiceResult.buildSuccessResult("操作成功", result, request);
    }


    @Override
    public ServiceResult<Boolean> restartOrder(RestartOrderRequest request) {
        Boolean result = changeOrderStatus(request.getOrderId(), OrderStatusEnum.START, OrderStatusEnum.STOP);
        return ServiceResult.buildSuccessResult("操作成功", result, request);
    }

    @Override
    public ServiceResult<Boolean> failOrderNode(FailOrderNodeRequest request) {
        orderNodeDao.makeOrderFault(request.getOrderNodeId(), NodeStatusEnum.FAULT.getCode(), NodeResultTypeEnum.FAULT.getCode(), request.getMsg());
        return ServiceResult.buildSuccessResult("处理成功", true, request);
    }

    @Override
    public ServiceResult<Boolean> dealOrderNode(DealOrderNodeRequest request) {
        /*前提:判断节点值是否允许*/

        /*1.结束当前工单节点(节点状态),处理结果类型->处理成功,处理结果id选择,处理人建议*/
        /*2.填充对应节点所需的属性值,*/
        /*3.将下一节点置为等待开始(并通知自动处理模块检测是否为自动处理模块)*/
        return null;
    }

    @Override
    public ServiceResult<Boolean> approvalOrder(ApprovalOrderRequest request) {
        return null;
    }

    /**
     * 通知工单监管人,进行审批处理,是否予以撤回
     *
     * @param request
     * @return
     */
    private boolean noticeMonitorUserIdAboutBackOrder(RecallOrderRequest request) {
        OrderInfoEntity byId = orderInfoDao.getById(request.getOrderId());
        String monitorUserId = byId.getMonitorUserId();
        PushMsgToSomeoneRequest pushMsgToSomeoneRequest = PushMsgToSomeoneRequest.build(request, monitorUserId, PushTypeEnum.EMAIL.getCode(), "工单撤回申请", request.getOrderId() + "工单申请撤回,请尽快审批,工单优先度:" + PriorityEnum.parse(byId.getPriority()).getName());
        ServiceResult serviceResult = DubboApiUtil.dubboApiTool("PushService", "pushMsgToSomeone", pushMsgToSomeoneRequest);
        if (serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            return true;
        }
        return false;
    }

    private Boolean changeOrderStatus(String orderId, OrderStatusEnum orderStatusEnum, OrderStatusEnum orderLastStatusEnum) {
        /*1.将总工单状态置为冻结*/
        Integer orderStatus = orderInfoDao.getOrderStatusById(orderId);
        // 只有状态为指定的状态的工单才能进行操作,否则操作失败
        if (orderLastStatusEnum.getCode().equals(orderStatus)) {
            orderInfoDao.changeOrderStatus(orderId, orderStatusEnum.getCode());
            return true;
        }
        return false;
    }

    private Boolean changeOrderStatus(String orderId, OrderStatusEnum orderStatusEnum) {
        /*1.将总工单状态置为冻结*/
        Integer orderStatus = orderInfoDao.getOrderStatusById(orderId);
        // 只有状态为指定的状态的工单才能进行操作,否则操作失败
        if (OrderStatusEnum.START.getCode().equals(orderStatus)) {
            orderInfoDao.changeOrderStatus(orderId, orderStatusEnum.getCode());
            return true;
        }
        return false;
    }
}
