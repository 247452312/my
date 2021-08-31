package indi.uhyils.pojo.DTO;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月12日 15时33分
 */
public class OrderBaseNodeAboutDTO implements BaseDTO {

    /**
     * 基本工单节点
     */
    private OrderBaseNodeDTO orderBaseNode;

    /**
     * 此工单节点的所有属性
     */
    private List<OrderBaseNodeFieldDTO> orderBaseNodeFields;

    /**
     * 此工单节点结果的类型
     */
    private List<OrderBaseNodeResultTypeDTO> orderBaseNodeResultTypes;

    /**
     * 此工单节点的路由
     */
    private List<OrderBaseNodeRouteDTO> orderBaseNodeRoutes;

    public static OrderBaseNodeAboutDTO build(OrderBaseNodeDTO orderBaseNode, List<OrderBaseNodeFieldDTO> orderBaseNodeFields, List<OrderBaseNodeResultTypeDTO> orderBaseNodeResultTypes, List<OrderBaseNodeRouteDTO> orderBaseNodeRoutes) {
        OrderBaseNodeAboutDTO build = new OrderBaseNodeAboutDTO();
        build.setOrderBaseNode(orderBaseNode);
        build.setOrderBaseNodeFields(orderBaseNodeFields);
        build.setOrderBaseNodeResultTypes(orderBaseNodeResultTypes);
        build.setOrderBaseNodeRoutes(orderBaseNodeRoutes);
        return build;
    }

    public OrderBaseNodeDTO getOrderBaseNode() {
        return orderBaseNode;
    }

    public void setOrderBaseNode(OrderBaseNodeDTO orderBaseNode) {
        this.orderBaseNode = orderBaseNode;
    }

    public List<OrderBaseNodeFieldDTO> getOrderBaseNodeFields() {
        return orderBaseNodeFields;
    }

    public void setOrderBaseNodeFields(List<OrderBaseNodeFieldDTO> orderBaseNodeFields) {
        this.orderBaseNodeFields = orderBaseNodeFields;
    }

    public List<OrderBaseNodeResultTypeDTO> getOrderBaseNodeResultTypes() {
        return orderBaseNodeResultTypes;
    }

    public void setOrderBaseNodeResultTypes(List<OrderBaseNodeResultTypeDTO> orderBaseNodeResultTypes) {
        this.orderBaseNodeResultTypes = orderBaseNodeResultTypes;
    }

    public List<OrderBaseNodeRouteDTO> getOrderBaseNodeRoutes() {
        return orderBaseNodeRoutes;
    }

    public void setOrderBaseNodeRoutes(List<OrderBaseNodeRouteDTO> orderBaseNodeRoutes) {
        this.orderBaseNodeRoutes = orderBaseNodeRoutes;
    }
}
