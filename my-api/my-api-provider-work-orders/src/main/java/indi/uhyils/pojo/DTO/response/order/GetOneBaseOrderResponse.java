package indi.uhyils.pojo.DTO.response.order;

import indi.uhyils.pojo.DO.OrderBaseInfoDO;
import java.io.Serializable;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月12日 14时44分
 */
public class GetOneBaseOrderResponse implements Serializable {

    /**
     * 工单本体
     */
    private OrderBaseInfoDO baseInfo;

    /**
     * 这个工单相关的节点
     */
    private List<OrderBaseNodeAboutResponse> orderBaseNodes;

    public static GetOneBaseOrderResponse build(OrderBaseInfoDO baseInfo, List<OrderBaseNodeAboutResponse> orderBaseNodes) {
        GetOneBaseOrderResponse build = new GetOneBaseOrderResponse();
        build.setBaseInfo(baseInfo);
        build.setOrderBaseNodes(orderBaseNodes);
        return build;

    }

    public OrderBaseInfoDO getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(OrderBaseInfoDO baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<OrderBaseNodeAboutResponse> getOrderBaseNodes() {
        return orderBaseNodes;
    }

    public void setOrderBaseNodes(List<OrderBaseNodeAboutResponse> orderBaseNodes) {
        this.orderBaseNodes = orderBaseNodes;
    }
}