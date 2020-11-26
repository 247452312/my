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
     * 申请类型
     */
    private String type;

    /**
     * 申请状态
     */
    private String status;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
