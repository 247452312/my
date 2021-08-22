package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月26日 17时50分
 */
public class OrderApplyDO extends BaseDoDO {

    /**
     * 申请人id
     */
    private Long applyUserId;

    /**
     * 工单id
     */
    private Long orderId;

    /**
     * 工单节点id
     */
    private Long orderNodeId;

    /**
     * 监管人id
     */
    private Long monitorUserId;

    /**
     * 目标人id
     */
    private Long targetUserId;

    /**
     * 申请类型{@link indi.uhyils.enum_.OrderApplyTypeEnum}
     */
    private Integer type;

    /**
     * 申请状态{@link indi.uhyils.enum_.OrderApplyStatusEnum}
     */
    private Integer status;


    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderNodeId() {
        return orderNodeId;
    }

    public void setOrderNodeId(Long orderNodeId) {
        this.orderNodeId = orderNodeId;
    }

    public Long getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(Long monitorUserId) {
        this.monitorUserId = monitorUserId;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
