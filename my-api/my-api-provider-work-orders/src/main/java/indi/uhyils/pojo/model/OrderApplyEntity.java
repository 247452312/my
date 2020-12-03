package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月26日 17时50分
 */
public class OrderApplyEntity extends BaseVoEntity {

    /**
     * 申请人id
     */
    private String applyUserId;

    /**
     * 工单id
     */
    private String orderId;
    /**
     * 工单节点id
     */
    private String orderNodeId;

    /**
     * 监管人id
     */
    private String monitorUserId;

    /**
     * 目标人id
     */
    private String targetUserId;

    /**
     * 申请类型{@link indi.uhyils.enum_.OrderApplyTypeEnum}
     */
    private Integer type;

    /**
     * 申请状态{@link indi.uhyils.enum_.OrderApplyStatusEnum}
     */
    private Integer status;


    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNodeId() {
        return orderNodeId;
    }

    public void setOrderNodeId(String orderNodeId) {
        this.orderNodeId = orderNodeId;
    }

    public String getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(String monitorUserId) {
        this.monitorUserId = monitorUserId;
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

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }
}
