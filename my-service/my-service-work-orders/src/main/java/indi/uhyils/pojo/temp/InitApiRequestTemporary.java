package indi.uhyils.pojo.temp;

import indi.uhyils.pojo.model.OrderNodeEntity;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 17时12分
 */
public class InitApiRequestTemporary implements Serializable {

    /**
     * 工单节点
     */
    private OrderNodeEntity orderNode;

    /**
     * 上一个工单节点
     */
    private OrderNodeEntity pervOrderNode;

    public OrderNodeEntity getOrderNode() {
        return orderNode;
    }

    public void setOrderNode(OrderNodeEntity orderNode) {
        this.orderNode = orderNode;
    }

    public OrderNodeEntity getPervOrderNode() {
        return pervOrderNode;
    }

    public void setPervOrderNode(OrderNodeEntity pervOrderNode) {
        this.pervOrderNode = pervOrderNode;
    }
}
