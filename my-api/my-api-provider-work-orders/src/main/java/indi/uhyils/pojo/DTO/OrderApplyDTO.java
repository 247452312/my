package indi.uhyils.pojo.DTO;


/**
 * (OrderApply)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分51秒
 */
public class OrderApplyDTO extends IdDTO {

    private static final long serialVersionUID = 874333092552231306L;


    /**
     * 申请人,上一个节点的处理人
     */
    private Long applyUserId;

    /**
     * 申请处理的工单id
     */
    private Long orderId;

    /**
     * 工单节点id
     */
    private Long orderNodeId;

    /**
     * 此工单监管人id
     */
    private Long monitorUserId;

    /**
     * 目标人id
     */
    private Long targetUserId;

    /**
     * 申请类型 0->转交申请
     */
    private Integer type;

    /**
     * 申请状态 0->未查看 1->未受理 2->已受理 3->已同意 4->已驳回
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
