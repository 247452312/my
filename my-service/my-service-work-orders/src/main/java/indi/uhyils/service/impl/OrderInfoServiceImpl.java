package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderInfoAssembler;
import indi.uhyils.builder.OrderNodeFieldValueBuilder;
import indi.uhyils.enum_.OrderStatusEnum;
import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.event.AgreeRecallOrderEvent;
import indi.uhyils.pojo.cqe.event.ApprovalOrderEvent;
import indi.uhyils.pojo.cqe.command.CommitOrderCommand;
import indi.uhyils.pojo.cqe.command.FrozenOrderCommand;
import indi.uhyils.pojo.cqe.query.GetAllOrderQuery;
import indi.uhyils.pojo.cqe.command.RecallOrderCommand;
import indi.uhyils.pojo.cqe.command.RestartOrderCommand;
import indi.uhyils.pojo.entity.OrderInfo;
import indi.uhyils.repository.OrderInfoRepository;
import indi.uhyils.service.OrderInfoService;
import indi.uhyils.util.LogUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * 工单基础信息样例表(OrderInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分14秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_info"})
public class OrderInfoServiceImpl extends AbstractDoService<OrderInfoDO, OrderInfo, OrderInfoDTO, OrderInfoRepository, OrderInfoAssembler> implements OrderInfoService {

    public OrderInfoServiceImpl(OrderInfoAssembler assembler, OrderInfoRepository repository) {
        super(assembler, repository);
    }


    @Override
    public ArrayList<OrderInfoDTO> getAllOrder(GetAllOrderQuery request) {
        ArrayList<OrderInfoDO> result = dao.getOrderByType(request.getType());
        return ServiceResult.buildSuccessResult(result);
    }


    @Override
    public Boolean commitOrder(CommitOrderCommand request) {
        Map<Long, String> value = request.getValue();
        List<OrderNodeFieldValueDO> orderNodeFieldValueEntities = OrderNodeFieldValueBuilder.buildOrderNodeFieldValues(value);
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
        OrderInfoDO orderInfo = orderInfoDao.getById(request.getOrderId());
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
        List<OrderNodeDO> orderNodeEntities = orderNodeDao.getByIds(nodeIds);
        for (OrderNodeDO orderNodeEntity : orderNodeEntities) {
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

        return ServiceResult.buildSuccessResult("提交成功", true);
    }

    @Override
    public Boolean recallOrder(RecallOrderCommand request) {
        Boolean result = changeOrderStatus(request.getOrderId(), OrderStatusEnum.WITHDRAWING);
        if (result) {
            /*工单状态修改完成后通知工单监管人,进行审批处理,是否予以撤回,注意,此处返回值为是否发送申请成功*/
            boolean b = noticeMonitorUserIdAboutBackOrder(request);
            if (!b) {
                return ServiceResult.buildFailedResult("操作失败,推送系统异常", false);
            }
            return ServiceResult.buildSuccessResult("操作成功,等待审批人审批", true);
        }
        return ServiceResult.buildFailedResult("操作失败", false);
    }

    @Override
    public Boolean agreeRecallOrder(AgreeRecallOrderEvent request) {
        Boolean result = changeOrderStatus(request.getOrderId(), OrderStatusEnum.WITHDRAWED);
        return ServiceResult.buildSuccessResult("操作成功", result);
    }

    @Override
    public Boolean frozenOrder(FrozenOrderCommand request) {
        Boolean result = changeOrderStatus(request.getOrderId(), OrderStatusEnum.STOP);
        return ServiceResult.buildSuccessResult("操作成功", result);
    }

    @Override
    public Boolean restartOrder(RestartOrderCommand request) {
        Boolean result = changeOrderStatus(request.getOrderId(), OrderStatusEnum.START, OrderStatusEnum.STOP);
        return ServiceResult.buildSuccessResult("操作成功", result);
    }

    @Override
    public Boolean approvalOrder(ApprovalOrderEvent request) throws Exception {
        return null;
    }
}
