package indi.uhyils.builder;

import indi.uhyils.enum_.OrderApplyStatusEnum;
import indi.uhyils.enum_.OrderApplyTypeEnum;
import indi.uhyils.pojo.model.OrderApplyEntity;
import indi.uhyils.pojo.model.OrderNodeEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月26日 18时15分
 */
public class OrderApplyBuilder {

    /**
     * 根据工单节点创建转交申请
     *
     * @param orderNode
     * @return
     */
    public static OrderApplyEntity buildTransApplyByOrderNode(OrderNodeEntity orderNode, String monitorUserId) {
        OrderApplyEntity orderApplyEntity = new OrderApplyEntity();
        orderApplyEntity.setApplyUserId(orderNode.getNoticeUserId());
        orderApplyEntity.setOrderId(orderNode.getBaseInfoId());
        orderApplyEntity.setOrderNodeId(orderNode.getId());
        orderApplyEntity.setMonitorUserId(monitorUserId);
        orderApplyEntity.setType(OrderApplyTypeEnum.TRANS.getCode());
        orderApplyEntity.setStatus(OrderApplyStatusEnum.NO_SEE.getCode());
        return orderApplyEntity;

    }
}
