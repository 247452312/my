package indi.uhyils.pojo.DTO;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月12日 14时44分
 */
public class GetOneOrderDTO implements BaseDTO {

    /**
     * 工单本体
     */
    private OrderInfoDTO info;

    /**
     * 这个工单相关的节点
     */
    private List<OrderNodeAboutDTO> orderNodes;

    public static GetOneOrderDTO build(OrderInfoDTO info, List<OrderNodeAboutDTO> orderNodes) {
        GetOneOrderDTO build = new GetOneOrderDTO();
        build.setInfo(info);
        build.setOrderNodes(orderNodes);
        return build;

    }

    public OrderInfoDTO getInfo() {
        return info;
    }

    public void setInfo(OrderInfoDTO info) {
        this.info = info;
    }

    public List<OrderNodeAboutDTO> getOrderNodes() {
        return orderNodes;
    }

    public void setOrderNodes(List<OrderNodeAboutDTO> orderNodes) {
        this.orderNodes = orderNodes;
    }
}
