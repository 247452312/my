package indi.uhyils.pojo.response.order;

import indi.uhyils.pojo.model.OrderInfoEntity;
import java.io.Serializable;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月12日 14时44分
 */
public class GetOneOrderResponse implements Serializable {

    /**
     * 工单本体
     */
    private OrderInfoEntity info;

    /**
     * 这个工单相关的节点
     */
    private List<OrderNodeAboutResponse> orderNodes;

    public static GetOneOrderResponse build(OrderInfoEntity info, List<OrderNodeAboutResponse> orderNodes) {
        GetOneOrderResponse build = new GetOneOrderResponse();
        build.setInfo(info);
        build.setOrderNodes(orderNodes);
        return build;

    }

    public OrderInfoEntity getInfo() {
        return info;
    }

    public void setInfo(OrderInfoEntity info) {
        this.info = info;
    }

    public List<OrderNodeAboutResponse> getOrderNodes() {
        return orderNodes;
    }

    public void setOrderNodes(List<OrderNodeAboutResponse> orderNodes) {
        this.orderNodes = orderNodes;
    }
}
