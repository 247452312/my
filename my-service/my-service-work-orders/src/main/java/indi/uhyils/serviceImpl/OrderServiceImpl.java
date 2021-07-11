package indi.uhyils.serviceImpl;

import indi.uhyils.builder.OrderApplyBuilder;
import indi.uhyils.builder.OrderNodeFieldValueBuilder;
import indi.uhyils.builder.OrderNodeResultTypeBuilder;
import indi.uhyils.builder.OrderNodeRouteBuilder;
import indi.uhyils.content.Content;
import indi.uhyils.content.OrderContent;
import indi.uhyils.dao.*;
import indi.uhyils.enum_.*;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.pojo.model.*;
import indi.uhyils.pojo.model.base.BaseIdEntity;
import indi.uhyils.pojo.request.*;
import indi.uhyils.pojo.request.base.IdRequest;
import indi.uhyils.pojo.response.DealOrderNodeResponse;
import indi.uhyils.pojo.response.InitOrderResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.temp.CheckNodeFieldResultTemporary;
import indi.uhyils.pojo.temp.InitApiRequestTemporary;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.rpc.spring.util.RpcApiUtil;
import indi.uhyils.service.OrderService;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.OrderBuilder;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时34分
 */
@RpcService
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
    private OrderApplyDao orderApplyDao;

    @Resource
    private OrderNodeFieldValueDao orderNodeFieldValueDao;

    @Override
    public ServiceResult<InitOrderResponse> initOrder(IdRequest request) throws Exception {
        //插入order基础信息
        Long baseInfoId = request.getId();
        OrderBaseInfoEntity baseInfo = orderBaseInfoDao.getById(baseInfoId);
        OrderInfoEntity orderInfoEntity = OrderBuilder.transBaseInfo2Info(baseInfo);
        orderInfoEntity.preInsert(request);
        orderInfoDao.insert(orderInfoEntity);

        // 获取基础表对应的所有节点
        List<OrderBaseNodeEntity> nodeList = orderBaseNodeDao.getNoHiddenByOrderId(baseInfoId);

        Long infoId = orderInfoEntity.getId();
        // 保存所有的路由, 路由比较特殊 需要改node的id
        List<OrderNodeRouteEntity> allRouteEntities = new ArrayList<>();
        // 报存所有的新老node对应关系
        Map<Long, Long> oldToNew = new HashMap<>(nodeList.size());
        // 创建之后的首节点的属性(返回给前台用)
        ArrayList<OrderNodeFieldEntity> orderNodeField = new ArrayList<>();
        // 每个节点的处理人(返回给前台用)
        HashMap<Long, Long> dealUserIds = new HashMap<>(nodeList.size());
        // 每个节点的抄送人(返回给前台用)
        HashMap<Long, Long> noticeUserIds = new HashMap<>(nodeList.size());

        //获取基础节点对应的所有信息(属性,结果类型,路由) 并转换成实例信息插入实例表
        nodeList.forEach(node -> {

            Boolean isStartNode = OrderNodeTypeEnum.START.getCode().equals(node.getType());

            //转换节点本身
            OrderNodeEntity orderNodeEntity = OrderBuilder.transBaseNode2Node(node, infoId);
            try {
                orderNodeEntity.preInsert(request);
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
            orderNodeDao.insert(orderNodeEntity);

            // 填充默认处理人与抄送人
            dealUserIds.put(orderNodeEntity.getId(), orderNodeEntity.getRunDealUserId());
            noticeUserIds.put(orderNodeEntity.getId(), orderNodeEntity.getNoticeUserId());

            oldToNew.put(node.getId(), orderNodeEntity.getId());
            //获取节点相关的所有东西(属性,结果类型,路由)
            List<OrderBaseNodeFieldEntity> baseNodeFieldEntity = orderBaseNodeFieldDao.getByOrderNodeId(node.getId());
            List<OrderBaseNodeResultTypeEntity> baseNodeResultTypeEntity = orderBaseNodeResultTypeDao.getByOrderNodeId(node.getId());
            List<OrderBaseNodeRouteEntity> baseRouteEntity = orderBaseNodeRouteDao.getByOrderNodeId(node.getId());

            Long id = orderNodeEntity.getId();
            baseNodeFieldEntity.stream().map(field -> OrderBuilder.transBaseField2Field(field, id)).forEach(field -> {
                if (isStartNode) {
                    orderNodeField.add(field);
                }
                try {
                    field.preInsert(request);
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }
                orderNodeFieldDao.insert(field);
            });
            baseNodeResultTypeEntity.stream().map(resultType -> OrderBuilder.transBaseResultType2ResultType(resultType, id)).forEach(resultType -> {
                try {
                    resultType.preInsert(request);
                } catch (Exception e) {
                    LogUtil.error(this, e);
                }
                orderNodeResultTypeDao.insert(resultType);
            });
            allRouteEntities.addAll(baseRouteEntity.stream().map(route -> OrderBuilder.transBaseRoute2Route(route, id)).collect(Collectors.toList()));
        });
        allRouteEntities.forEach(t -> {
            t.setPrevNodeId(oldToNew.get(t.getPrevNodeId()));
            t.setNextNodeId(oldToNew.get(t.getNextNodeId()));
            try {
                t.preInsert(request);
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
            orderNodeRouteDao.insert(t);
        });

        InitOrderResponse build = InitOrderResponse.build(infoId, orderNodeField, baseInfo.getMonitorUserId(), dealUserIds, noticeUserIds);
        return ServiceResult.buildSuccessResult("插入成功", build, request);
    }

    @Override
    public ServiceResult<Boolean> commitOrder(CommitOrderRequest request) {
        Map<Long, String> value = request.getValue();
        List<OrderNodeFieldValueEntity> orderNodeFieldValueEntities = OrderNodeFieldValueBuilder.buildOrderNodeFieldValues(value);
        /*添加首节点的真实值*/
        orderNodeFieldValueEntities.forEach(t -> {
            try {
                t.preInsert(request);
            } catch (Exception e) {
                LogUtil.error(this, e);
            }
            orderNodeFieldValueDao.insert(t);
        });
        /*更新主表的监管人*/
        OrderInfoEntity orderInfo = orderInfoDao.getById(request.getOrderId());
        Long monitorUserId = orderInfo.getMonitorUserId();
        if (monitorUserId == null || !monitorUserId.equals(request.getMonitorUserId())) {
            orderInfo.setMonitorUserId(request.getMonitorUserId());
            orderInfo.preUpdate(request);
            orderInfoDao.update(orderInfo);
        }

        /*更新节点表的处理人,抄送人*/
        Map<Long, Long> dealUserIds = request.getDealUserIds();
        Map<Long, Long> noticeUserIds = request.getNoticeUserIds();
        Set<Long> nodeIds = new HashSet<>();
        nodeIds.addAll(dealUserIds.keySet());
        nodeIds.addAll(noticeUserIds.keySet());
        List<OrderNodeEntity> orderNodeEntities = orderNodeDao.getByIds(nodeIds);
        for (OrderNodeEntity orderNodeEntity : orderNodeEntities) {
            boolean update = false;
            Long orderDealUserId = dealUserIds.get(orderNodeEntity.getId());
            Long noticeUserId = noticeUserIds.get(orderNodeEntity.getId());
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
        orderNodeDao.makeOrderFault(request.getOrderNodeId(), OrderNodeStatusEnum.FAULT.getCode(), OrderNodeResultTypeEnum.FAULT.getCode(), request.getMsg());
        return ServiceResult.buildSuccessResult("处理成功", true, request);
    }

    @Override
    public ServiceResult<DealOrderNodeResponse> dealOrderNode(DealOrderNodeRequest request) throws Exception {
        /*前提:判断节点值是否允许*/
        CheckNodeFieldResultTemporary checkNodeFieldResult = checkNodeAllow(request.getOrderNodeFieldValueMap());
        if (!checkNodeFieldResult.getAllow()) {
            return ServiceResult.buildFailedResult("节点值判断出错", DealOrderNodeResponse.buildCheckFaild(checkNodeFieldResult.getAllow(), checkNodeFieldResult.getDetailResult()), request);
        }
        /*1.结束当前工单节点(节点状态),处理结果类型->处理成功,处理结果id选择,处理人建议*/
        Long nodeId = request.getNodeId();
        OrderNodeEntity orderNode = orderNodeDao.getById(nodeId);
        orderNode.setStatus(OrderNodeStatusEnum.OVER.getCode());
        orderNode.setResultType(OrderNodeResultTypeEnum.SUCCESS.getCode());
        orderNode.setResultId(request.getResultId());
        orderNode.setSuggest(request.getSuggest());
        orderNode.preUpdate(request);
        orderNodeDao.update(orderNode);
        /*2.填充对应节点所需的属性值,*/
        Map<Long, Object> orderNodeFieldValueMap = request.getOrderNodeFieldValueMap();
        for (Map.Entry<Long, Object> entry : orderNodeFieldValueMap.entrySet()) {
            OrderNodeFieldValueEntity t = new OrderNodeFieldValueEntity();
            Long key = entry.getKey();
            Object value = entry.getValue();
            t.setNodeFieldId(key);
            t.setRealValue(String.valueOf(value));
            t.preInsert(request);
            orderNodeFieldValueDao.insert(t);
        }
        /*3.将下一节点置为等待开始(并通知自动处理模块检测是否为自动处理模块)*/
        OrderNodeEntity nextNode = orderNodeDao.getNextNodeByNodeAndResult(request.getNodeId(), request.getResultId());
        nextNode.setStatus(OrderNodeStatusEnum.WAIT_STATUS.getCode());
        nextNode.preUpdate(request);
        orderNodeDao.update(nextNode);
        Integer runType = nextNode.getRunType();
        if (OrderNodeRunTypeEnum.AUTO.getCode().equals(runType)) {
            noticeAutoDealOrder(nextNode.getId(), request.getNodeId());
        }
        return ServiceResult.buildSuccessResult("处理成功", DealOrderNodeResponse.buildSuccess(), request);
    }

    /**
     * 通知下一个节点(通过MQ)
     *
     * @param nextNodeId
     * @param nodeId
     */
    private void noticeAutoDealOrder(Long nextNodeId, Long nodeId) throws IOException, TimeoutException {
        InitApiRequestTemporary msg = new InitApiRequestTemporary();
        msg.setOrderNode(orderNodeDao.getById(nextNodeId));
        msg.setPervOrderNode(orderNodeDao.getById(nodeId));
        MqUtil.sendMsg(OrderContent.ORDER_EXCHANGE, OrderContent.ORDER_AUTO_NODE_SEND_QUEUE, msg);
    }

    @Override
    public ServiceResult<Boolean> incapacityFailOrderNode(IncapacityFailOrderNodeRequest request) throws Exception {
        Long orderNodeId = request.getOrderNodeId();
        OrderNodeEntity orderNode = orderNodeDao.getById(orderNodeId);
        /*1.将节点状态置为转交中*/
        orderNode.setStatus(OrderNodeStatusEnum.TRANSFER.getCode());
        orderNode.preUpdate(request);
        orderNodeDao.update(orderNode);
        /*2.插入申请表*/
        OrderInfoEntity order = orderInfoDao.getById(orderNode.getBaseInfoId());
        OrderApplyEntity orderApply = OrderApplyBuilder.buildTransApplyByOrderNode(orderNode, order.getMonitorUserId());
        orderApply.preInsert(request);
        orderApplyDao.insert(orderApply);
        /*3.通知审批人*/
        ServiceResult<String> userName = (ServiceResult) RpcApiUtil.rpcApiTool("UserService", "getNameById", IdRequest.build(request, request.getRecommendUserId()));
        PushMsgToSomeoneRequest pushMsgToSomeoneRequest = PushMsgToSomeoneRequest.build(request, order.getMonitorUserId(), PushTypeEnum.EMAIL.getCode(), "工单节点转交申请", order.getId() + "工单转交撤回,请尽快审批,转交目标人:" + userName.getData().toString());
        RpcApiUtil.rpcApiTool("PushService", "pushMsgToSomeone", pushMsgToSomeoneRequest);
        return ServiceResult.buildSuccessResult(true, request);
    }

    @Override
    public ServiceResult<Boolean> approvalOrder(ApprovalOrderRequest request) throws Exception {
        Long orderApplyId = request.getOrderApplyId();
        OrderApplyEntity orderApply = orderApplyDao.getById(orderApplyId);

        /*0.将此节点状态置位已转交*/
        Long orderNodeId = orderApply.getOrderNodeId();
        OrderNodeEntity orderNode = orderNodeDao.getById(orderNodeId);
        orderNode.setStatus(OrderNodeStatusEnum.TRANSFERRED.getCode());
        orderNode.preUpdate(request);
        orderNodeDao.update(orderNode);
        Long lastOrderNodeId = orderNode.getId();

        /*1.新增下一节点,复制此节点的参数,状态置为停用*/
        orderNode.setStatus(OrderStatusEnum.STOP.getCode());
        orderNode.setName(orderNode.getName() + "(转交)");
        orderNode.setRunDealUserId(orderApply.getTargetUserId());
        orderNode.preInsert(request);

        Long newOrderNodeId = orderNode.getId();

        /*2.将此节点的属性,结果,路由复制到下一个节点上去*/
        // 属性
        List<OrderNodeFieldEntity> orderNodeFields = orderNodeFieldDao.getByOrderNodeId(lastOrderNodeId);
        for (OrderNodeFieldEntity orderNodeField : orderNodeFields) {
            orderNodeField.setBaseOrderNodeId(newOrderNodeId);
            orderNodeField.preInsert(request);
            orderNodeFieldDao.insert(orderNodeField);
        }

        //结果
        List<OrderNodeResultTypeEntity> orderNodeResultTypes = orderNodeResultTypeDao.getByOrderNodeId(lastOrderNodeId);
        for (OrderNodeResultTypeEntity orderNodeResultType : orderNodeResultTypes) {
            orderNodeResultType.setBaseNodeId(newOrderNodeId);
            orderNodeResultType.preInsert(request);
            orderNodeResultTypeDao.insert(orderNodeResultType);
        }

        // 路由
        List<OrderNodeRouteEntity> orderNodeRoutes = orderNodeRouteDao.getByPrevOrderNodeId(lastOrderNodeId);
        for (OrderNodeRouteEntity orderNodeRoute : orderNodeRoutes) {
            orderNodeRoute.setPrevNodeId(newOrderNodeId);
            orderNodeRoute.preInsert(request);
            orderNodeRouteDao.insert(orderNodeRoute);
        }

        /*3.新增此节点已转交的'结果'并将此节点的'结果'置位此结果*/
        OrderNodeResultTypeEntity transResult = OrderNodeResultTypeBuilder.build(lastOrderNodeId, "转交");
        transResult.preInsert(request);
        orderNodeResultTypeDao.insert(transResult);
        orderNode.setResultId(transResult.getId());
        orderNode.setResultType(OrderNodeResultTypeEnum.TRANSFER.getCode());

        /*4.新增此节点到下一节点的路由*/
        OrderNodeRouteEntity newOrderNodeRoute = OrderNodeRouteBuilder.build(orderNodeId, transResult.getId(), newOrderNodeId);
        newOrderNodeRoute.preInsert(request);
        orderNodeRouteDao.insert(newOrderNodeRoute);

        /*5.将下一节点置位等待开始*/
        orderNode.setStatus(OrderNodeStatusEnum.WAIT_STATUS.getCode());
        // 新节点插入后置
        orderNodeDao.insert(orderNode);

        /*6.通知下一节点处理人*/
        Long targetUserId = orderApply.getTargetUserId();
        OrderInfoEntity orderInfo = orderInfoDao.getById(orderNode.getBaseInfoId());
        PushMsgToSomeoneRequest pushMsgToSomeoneRequest = PushMsgToSomeoneRequest.build(request, targetUserId, PushTypeEnum.EMAIL.getCode(), "工单流转事务提示", orderNodeId + "工单已转交到你手,审批人通过,请尽快处理,工单优先度:" + OrderPriorityEnum.parse(orderInfo.getPriority()).getName());
        RpcApiUtil.rpcApiTool("PushService", "pushMsgToSomeone", pushMsgToSomeoneRequest);
        return ServiceResult.buildSuccessResult("审批成功", true, request);
    }

    /**
     * 通知工单监管人,进行审批处理,是否予以撤回
     *
     * @param request
     * @return
     */
    private boolean noticeMonitorUserIdAboutBackOrder(RecallOrderRequest request) {
        OrderInfoEntity byId = orderInfoDao.getById(request.getOrderId());
        Long monitorUserId = byId.getMonitorUserId();
        PushMsgToSomeoneRequest pushMsgToSomeoneRequest = PushMsgToSomeoneRequest.build(request, monitorUserId, PushTypeEnum.EMAIL.getCode(), "工单撤回申请", request.getOrderId() + "工单申请撤回,请尽快审批,工单优先度:" + OrderPriorityEnum.parse(byId.getPriority()).getName());
        ServiceResult serviceResult = (ServiceResult) RpcApiUtil.rpcApiTool("PushService", "pushMsgToSomeone", pushMsgToSomeoneRequest);
        if (serviceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 修改工单状态(带有检查)
     *
     * @param orderId             工单id
     * @param orderStatusEnum     工单要修改的状态
     * @param orderLastStatusEnum 工单原本状态
     * @return
     */
    private Boolean changeOrderStatus(Long orderId, OrderStatusEnum orderStatusEnum, OrderStatusEnum orderLastStatusEnum) {
        /*1.将总工单状态置为冻结*/
        Integer orderStatus = orderInfoDao.getOrderStatusById(orderId);
        // 只有状态为指定的状态的工单才能进行操作,否则操作失败
        if (orderLastStatusEnum.getCode().equals(orderStatus)) {
            orderInfoDao.changeOrderStatus(orderId, orderStatusEnum.getCode());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 修改工单状态(带有检查,默认原本状态为启动)
     *
     * @param orderId         工单id
     * @param orderStatusEnum 工单要修改的状态
     * @return
     */
    private Boolean changeOrderStatus(Long orderId, OrderStatusEnum orderStatusEnum) {
        /*1.将总工单状态置为冻结*/
        Integer orderStatus = orderInfoDao.getOrderStatusById(orderId);
        // 只有状态为指定的状态的工单才能进行操作,否则操作失败
        if (OrderStatusEnum.START.getCode().equals(orderStatus)) {
            orderInfoDao.changeOrderStatus(orderId, orderStatusEnum.getCode());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    /**
     * 检查输入值是否符合要求
     *
     * @param orderNodeFieldValueMap
     * @return
     */
    private CheckNodeFieldResultTemporary checkNodeAllow(Map<Long, Object> orderNodeFieldValueMap) {
        CheckNodeFieldResultTemporary result = new CheckNodeFieldResultTemporary();
        result.setDetailResult(new HashMap<>(orderNodeFieldValueMap.size()));
        //默认正确
        result.setAllow(true);

        Set<Long> fieldIds = orderNodeFieldValueMap.keySet();
        List<OrderNodeFieldEntity> fieldList = orderNodeFieldDao.getByIds(fieldIds);
        Map<Long, OrderNodeFieldEntity> fieldMap = fieldList.stream().collect(Collectors.toMap(BaseIdEntity::getId, t -> t));
        for (Map.Entry<Long, Object> entry : orderNodeFieldValueMap.entrySet()) {
            Long orderNodeFieldId = entry.getKey();
            Object value = entry.getValue();
            OrderNodeFieldEntity orderNodeFieldEntity = fieldMap.get(orderNodeFieldId);
            // 如果前台没有传值,则使用默认值
            if (value == null) {
                orderNodeFieldValueMap.put(orderNodeFieldId, orderNodeFieldEntity.getDefaultValue());
                result.put(orderNodeFieldEntity, true);
                continue;
            }
            // 如果不能为空 并且传入值为空了,就错了
            if (Content.BLACK.equals(value) && !orderNodeFieldEntity.getEmpty()) {
                result.put(orderNodeFieldEntity, false);
            }
            switch (OrderNodeFieldValueTypeEnum.parse(orderNodeFieldEntity.getType())) {
                case STRING:
                    result.put(orderNodeFieldEntity, true);
                    continue;
                case EMAIL:
                    result.put(orderNodeFieldEntity, String.valueOf(value).matches(Content.EMAIL_REGEX));
                    continue;
                case DATE:
                    result.put(orderNodeFieldEntity, String.valueOf(value).matches(Content.DATE_REGEX));
                    continue;
                case VALUE:
                    result.put(orderNodeFieldEntity, String.valueOf(value).matches(Content.VALUE_REGEX));
                    continue;
                case ENGLISH:
                    result.put(orderNodeFieldEntity, String.valueOf(value).matches(Content.ENGLISH_REGEX));
                    continue;
                default:
                    result.put(orderNodeFieldEntity, false);
            }
        }
        return result;
    }
}
