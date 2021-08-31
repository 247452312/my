package indi.uhyils.serviceImpl;

import com.alibaba.fastjson.JSON;
import indi.uhyils.builder.OrderNodeResultTypeBuilder;
import indi.uhyils.builder.OrderNodeRouteBuilder;
import indi.uhyils.content.OrderContent;
import indi.uhyils.context.MyContext;
import indi.uhyils.dao.OrderApplyDao;
import indi.uhyils.dao.OrderBaseInfoDao;
import indi.uhyils.dao.OrderBaseNodeDao;
import indi.uhyils.dao.OrderBaseNodeFieldDao;
import indi.uhyils.dao.OrderBaseNodeResultTypeDao;
import indi.uhyils.dao.OrderBaseNodeRouteDao;
import indi.uhyils.dao.OrderInfoDao;
import indi.uhyils.dao.OrderNodeDao;
import indi.uhyils.dao.OrderNodeFieldDao;
import indi.uhyils.dao.OrderNodeFieldValueDao;
import indi.uhyils.dao.OrderNodeResultTypeDao;
import indi.uhyils.dao.OrderNodeRouteDao;
import indi.uhyils.enum_.OrderNodeFieldValueTypeEnum;
import indi.uhyils.enum_.OrderNodeResultTypeEnum;
import indi.uhyils.enum_.OrderNodeStatusEnum;
import indi.uhyils.enum_.OrderPriorityEnum;
import indi.uhyils.enum_.OrderStatusEnum;
import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.pojo.DO.OrderApplyDO;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.DO.OrderNodeRouteDO;
import indi.uhyils.pojo.DO.base.BaseIdDO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.event.ApprovalOrderEvent;
import indi.uhyils.pojo.DTO.request.PushMsgToSomeoneRequest;
import indi.uhyils.pojo.cqe.command.RecallOrderCommand;
import indi.uhyils.pojo.temp.CheckNodeFieldResultTemporary;
import indi.uhyils.pojo.temp.InitApiRequestTemporary;
import indi.uhyils.protocol.rpc.OrderProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.util.RpcApiUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时34分
 */
@RpcService
@Transactional(rollbackFor = Exception.class)
public class OrderProviderImpl implements OrderProvider {

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
        MqUtil.sendMsg(OrderContent.ORDER_EXCHANGE, OrderContent.ORDER_AUTO_NODE_SEND_QUEUE, JSON.toJSONString(msg));
    }

    @Override
    public ServiceResult<Boolean> approvalOrder(ApprovalOrderEvent request) throws Exception {
        Long orderApplyId = request.getOrderApplyId();
        OrderApplyDO orderApply = orderApplyDao.getById(orderApplyId);

        /*0.将此节点状态置位已转交*/
        Long orderNodeId = orderApply.getOrderNodeId();
        OrderNodeDO orderNode = orderNodeDao.getById(orderNodeId);
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
        List<OrderNodeFieldDO> orderNodeFields = orderNodeFieldDao.getByOrderNodeId(lastOrderNodeId);
        for (OrderNodeFieldDO orderNodeField : orderNodeFields) {
            orderNodeField.setBaseOrderNodeId(newOrderNodeId);
            orderNodeField.preInsert(request);
            orderNodeFieldDao.insert(orderNodeField);
        }

        //结果
        List<OrderNodeResultTypeDO> orderNodeResultTypes = orderNodeResultTypeDao.getByOrderNodeId(lastOrderNodeId);
        for (OrderNodeResultTypeDO orderNodeResultType : orderNodeResultTypes) {
            orderNodeResultType.setBaseNodeId(newOrderNodeId);
            orderNodeResultType.preInsert(request);
            orderNodeResultTypeDao.insert(orderNodeResultType);
        }

        // 路由
        List<OrderNodeRouteDO> orderNodeRoutes = orderNodeRouteDao.getByPrevOrderNodeId(lastOrderNodeId);
        for (OrderNodeRouteDO orderNodeRoute : orderNodeRoutes) {
            orderNodeRoute.setPrevNodeId(newOrderNodeId);
            orderNodeRoute.preInsert(request);
            orderNodeRouteDao.insert(orderNodeRoute);
        }

        /*3.新增此节点已转交的'结果'并将此节点的'结果'置位此结果*/
        OrderNodeResultTypeDO transResult = OrderNodeResultTypeBuilder.build(lastOrderNodeId, "转交");
        transResult.preInsert(request);
        orderNodeResultTypeDao.insert(transResult);
        orderNode.setResultId(transResult.getId());
        orderNode.setResultType(OrderNodeResultTypeEnum.TRANSFER.getCode());

        /*4.新增此节点到下一节点的路由*/
        OrderNodeRouteDO newOrderNodeRoute = OrderNodeRouteBuilder.build(orderNodeId, transResult.getId(), newOrderNodeId);
        newOrderNodeRoute.preInsert(request);
        orderNodeRouteDao.insert(newOrderNodeRoute);

        /*5.将下一节点置位等待开始*/
        orderNode.setStatus(OrderNodeStatusEnum.WAIT_STATUS.getCode());
        // 新节点插入后置
        orderNodeDao.insert(orderNode);

        /*6.通知下一节点处理人*/
        Long targetUserId = orderApply.getTargetUserId();
        OrderInfoDO orderInfo = orderInfoDao.getById(orderNode.getBaseInfoId());
        PushMsgToSomeoneRequest pushMsgToSomeoneRequest = PushMsgToSomeoneRequest
            .build(request, targetUserId, PushTypeEnum.EMAIL.getCode(), "工单流转事务提示", orderNodeId + "工单已转交到你手,审批人通过,请尽快处理,工单优先度:" + OrderPriorityEnum.parse(orderInfo.getPriority()).getName());
        RpcApiUtil.rpcApiTool("PushService", "pushMsgToSomeone", pushMsgToSomeoneRequest);
        return ServiceResult.buildSuccessResult("审批成功", true);
    }

    /**
     * 通知工单监管人,进行审批处理,是否予以撤回
     *
     * @param request
     *
     * @return
     */
    private boolean noticeMonitorUserIdAboutBackOrder(RecallOrderCommand request) {
        OrderInfoDO byId = orderInfoDao.getById(request.getOrderId());
        Long monitorUserId = byId.getMonitorUserId();
        PushMsgToSomeoneRequest pushMsgToSomeoneRequest = PushMsgToSomeoneRequest
            .build(request, monitorUserId, PushTypeEnum.EMAIL.getCode(), "工单撤回申请", request.getOrderId() + "工单申请撤回,请尽快审批,工单优先度:" + OrderPriorityEnum.parse(byId.getPriority()).getName());
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
     *
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
     *
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
     *
     * @return
     */
    private CheckNodeFieldResultTemporary checkNodeAllow(Map<Long, Object> orderNodeFieldValueMap) {
        CheckNodeFieldResultTemporary result = new CheckNodeFieldResultTemporary();
        result.setDetailResult(new HashMap<>(orderNodeFieldValueMap.size()));
        //默认正确
        result.setAllow(true);

        Set<Long> fieldIds = orderNodeFieldValueMap.keySet();
        List<OrderNodeFieldDO> fieldList = orderNodeFieldDao.getByIds(fieldIds);
        Map<Long, OrderNodeFieldDO> fieldMap = fieldList.stream().collect(Collectors.toMap(BaseIdDO::getId, t -> t));
        for (Map.Entry<Long, Object> entry : orderNodeFieldValueMap.entrySet()) {
            Long orderNodeFieldId = entry.getKey();
            Object value = entry.getValue();
            OrderNodeFieldDO orderNodeFieldEntity = fieldMap.get(orderNodeFieldId);
            // 如果前台没有传值,则使用默认值
            if (value == null) {
                orderNodeFieldValueMap.put(orderNodeFieldId, orderNodeFieldEntity.getDefaultValue());
                result.put(orderNodeFieldEntity, true);
                continue;
            }
            // 如果不能为空 并且传入值为空了,就错了
            if (MyContext.BLACK.equals(value) && !orderNodeFieldEntity.getEmpty()) {
                result.put(orderNodeFieldEntity, false);
            }
            switch (OrderNodeFieldValueTypeEnum.parse(orderNodeFieldEntity.getType())) {
                case STRING:
                    result.put(orderNodeFieldEntity, true);
                    continue;
                case EMAIL:
                    result.put(orderNodeFieldEntity, String.valueOf(value).matches(MyContext.EMAIL_REGEX));
                    continue;
                case DATE:
                    result.put(orderNodeFieldEntity, String.valueOf(value).matches(MyContext.DATE_REGEX));
                    continue;
                case VALUE:
                    result.put(orderNodeFieldEntity, String.valueOf(value).matches(MyContext.VALUE_REGEX));
                    continue;
                case ENGLISH:
                    result.put(orderNodeFieldEntity, String.valueOf(value).matches(MyContext.ENGLISH_REGEX));
                    continue;
                default:
                    result.put(orderNodeFieldEntity, false);
            }
        }
        return result;
    }
}
