package indi.uhyils.serviceImpl;

import indi.uhyils.builder.OrderNodeFieldValueBuilder;
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
import indi.uhyils.pojo.response.InsertOrderResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.temp.CheckNodeFieldResultTemporary;
import indi.uhyils.pojo.temp.InitApiRequestTemporary;
import indi.uhyils.service.OrderService;
import indi.uhyils.util.DubboApiUtil;
import indi.uhyils.util.OrderBuilder;
import org.apache.dubbo.config.annotation.Service;
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
    public ServiceResult<DealOrderNodeResponse> dealOrderNode(DealOrderNodeRequest request) throws IOException, TimeoutException {
        /*前提:判断节点值是否允许*/
        CheckNodeFieldResultTemporary checkNodeFieldResult = checkNodeAllow(request.getOrderNodeFieldValueMap());
        if (!checkNodeFieldResult.getAllow()) {
            return ServiceResult.buildFailedResult("节点值判断出错", DealOrderNodeResponse.buildCheckFaild(checkNodeFieldResult.getAllow(), checkNodeFieldResult.getDetailResult()), request);
        }
        /*1.结束当前工单节点(节点状态),处理结果类型->处理成功,处理结果id选择,处理人建议*/
        String nodeId = request.getNodeId();
        OrderNodeEntity orderNode = orderNodeDao.getById(nodeId);
        orderNode.setStatus(NodeStatusEnum.OVER.getCode());
        orderNode.setResultType(NodeResultTypeEnum.SUCCESS.getCode());
        orderNode.setResultId(request.getResultId());
        orderNode.setSuggest(request.getSuggest());
        orderNode.preUpdate(request);
        orderNodeDao.update(orderNode);
        /*2.填充对应节点所需的属性值,*/
        Map<String, Object> orderNodeFieldValueMap = request.getOrderNodeFieldValueMap();
        for (Map.Entry<String, Object> entry : orderNodeFieldValueMap.entrySet()) {
            OrderNodeFieldValueEntity t = new OrderNodeFieldValueEntity();
            String key = entry.getKey();
            Object value = entry.getValue();
            t.setNodeFieldId(key);
            t.setRealValue(String.valueOf(value));
            t.preInsert(request);
            orderNodeFieldValueDao.insert(t);
        }
        /*3.将下一节点置为等待开始(并通知自动处理模块检测是否为自动处理模块)*/
        OrderNodeEntity nextNode = orderNodeDao.getNextNodeByNodeAndResult(request.getNodeId(), request.getResultId());
        nextNode.setStatus(NodeStatusEnum.WAIT_STATUS.getCode());
        nextNode.preUpdate(request);
        orderNodeDao.update(nextNode);
        Integer runType = nextNode.getRunType();
        if (NodeRunTypeEnum.AUTO.getCode().equals(runType)) {
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
    private void noticeAutoDealOrder(String nextNodeId, String nodeId) throws IOException, TimeoutException {
        InitApiRequestTemporary msg = new InitApiRequestTemporary();
        msg.setOrderNode(orderNodeDao.getById(nextNodeId));
        msg.setPervOrderNode(orderNodeDao.getById(nodeId));
        MqUtil.sendMsg(OrderContent.ORDER_EXCHANGE, OrderContent.ORDER_AUTO_NODE_SEND_QUEUE, msg);
    }

    @Override
    public ServiceResult<Boolean> incapacityFailOrderNode(IncapacityFailOrderNodeRequest request) {
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

    /**
     * 修改工单状态(带有检查)
     *
     * @param orderId             工单id
     * @param orderStatusEnum     工单要修改的状态
     * @param orderLastStatusEnum 工单原本状态
     * @return
     */
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

    /**
     * 修改工单状态(带有检查,默认原本状态为启动)
     *
     * @param orderId         工单id
     * @param orderStatusEnum 工单要修改的状态
     * @return
     */
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


    /**
     * 检查输入值是否符合要求
     *
     * @param orderNodeFieldValueMap
     * @return
     */
    private CheckNodeFieldResultTemporary checkNodeAllow(Map<String, Object> orderNodeFieldValueMap) {
        CheckNodeFieldResultTemporary result = new CheckNodeFieldResultTemporary();
        result.setDetailResult(new HashMap<>(orderNodeFieldValueMap.size()));
        //默认正确
        result.setAllow(true);

        Set<String> fieldIds = orderNodeFieldValueMap.keySet();
        List<OrderNodeFieldEntity> fieldList = orderNodeFieldDao.getByIds(fieldIds);
        Map<String, OrderNodeFieldEntity> fieldMap = fieldList.stream().collect(Collectors.toMap(BaseIdEntity::getId, t -> t));
        for (Map.Entry<String, Object> entry : orderNodeFieldValueMap.entrySet()) {
            String orderNodeFieldId = entry.getKey();
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
            switch (NodeFieldValueTypeEnum.parse(orderNodeFieldEntity.getType())) {
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
