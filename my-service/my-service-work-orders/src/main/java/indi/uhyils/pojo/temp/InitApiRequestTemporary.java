package indi.uhyils.pojo.temp;

import indi.uhyils.pojo.model.OrderNodeDO;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 17时12分
 */
public class InitApiRequestTemporary implements Serializable {

    /**
     * 工单节点
     */
    private OrderNodeDO orderNode;

    /**
     * 上一个工单节点
     */
    private OrderNodeDO pervOrderNode;

    public OrderNodeDO getOrderNode() {
        return orderNode;
    }

    public void setOrderNode(OrderNodeDO orderNode) {
        this.orderNode = orderNode;
    }

    public OrderNodeDO getPervOrderNode() {
        return pervOrderNode;
    }

    public void setPervOrderNode(OrderNodeDO pervOrderNode) {
        this.pervOrderNode = pervOrderNode;
    }
}
