package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseVoEntity;

/**
 * 订单流程图边表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月27日 01时06分
 */
public class OrderFlowSideEntity extends BaseVoEntity {

    /**
     * 边的尾部节点id
     */
    private String nextNodeId;

    /**
     * 工单id
     */
    private String orderId;

    /**
     * 边的头部的id
     */
    private String nodeId;


    public String getNextNodeId() {
        return nextNodeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getNodeId() {
        return nodeId;
    }


    public void setNextNodeId(String nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }


}
