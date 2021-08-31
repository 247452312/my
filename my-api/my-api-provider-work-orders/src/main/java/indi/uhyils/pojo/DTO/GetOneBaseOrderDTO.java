package indi.uhyils.pojo.DTO;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月12日 14时44分
 */
public class GetOneBaseOrderDTO implements BaseDTO {

    /**
     * 工单本体
     */
    private OrderBaseInfoDTO baseInfo;

    /**
     * 这个工单相关的节点
     */
    private List<OrderBaseNodeAboutDTO> orderBaseNodes;

    public static GetOneBaseOrderDTO build(OrderBaseInfoDTO baseInfo, List<OrderBaseNodeAboutDTO> orderBaseNodes) {
        GetOneBaseOrderDTO build = new GetOneBaseOrderDTO();
        build.setBaseInfo(baseInfo);
        build.setOrderBaseNodes(orderBaseNodes);
        return build;

    }

    public OrderBaseInfoDTO getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(OrderBaseInfoDTO baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<OrderBaseNodeAboutDTO> getOrderBaseNodes() {
        return orderBaseNodes;
    }

    public void setOrderBaseNodes(List<OrderBaseNodeAboutDTO> orderBaseNodes) {
        this.orderBaseNodes = orderBaseNodes;
    }
}
