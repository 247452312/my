package indi.uhyils.pojo.DTO;


import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月12日 15时33分
 */
public class OrderNodeAboutDTO implements BaseDTO {

    /**
     * 基本工单节点
     */
    private OrderNodeDTO orderNode;

    /**
     * 此工单节点的所有属性
     */
    private List<OrderNodeFieldDTO> orderNodeFields;

    /**
     * 此工单节点结果的类型
     */
    private List<OrderNodeResultTypeDTO> orderNodeResultTypes;

    /**
     * 此工单节点的路由
     */
    private List<OrderNodeRouteDTO> orderNodeRoutes;

    /**
     * 此节点的所有属性填写的值
     */
    private List<OrderNodeFieldValueDTO> orderNodeFieldValues;

    public static OrderNodeAboutDTO build(OrderNodeDTO orderNode, List<OrderNodeFieldDTO> orderNodeFields, List<OrderNodeResultTypeDTO> orderNodeResultTypes, List<OrderNodeRouteDTO> orderNodeRoutes, List<OrderNodeFieldValueDTO> orderNodeFieldValues) {
        OrderNodeAboutDTO build = new OrderNodeAboutDTO();
        build.setOrderNode(orderNode);
        build.setOrderNodeFields(orderNodeFields);
        build.setOrderNodeResultTypes(orderNodeResultTypes);
        build.setOrderNodeRoutes(orderNodeRoutes);
        build.setOrderNodeFieldValues(orderNodeFieldValues);
        return build;
    }

    public OrderNodeDTO getOrderNode() {
        return orderNode;
    }

    public void setOrderNode(OrderNodeDTO orderNode) {
        this.orderNode = orderNode;
    }

    public List<OrderNodeFieldDTO> getOrderNodeFields() {
        return orderNodeFields;
    }

    public void setOrderNodeFields(List<OrderNodeFieldDTO> orderNodeFields) {
        this.orderNodeFields = orderNodeFields;
    }

    public List<OrderNodeResultTypeDTO> getOrderNodeResultTypes() {
        return orderNodeResultTypes;
    }

    public void setOrderNodeResultTypes(List<OrderNodeResultTypeDTO> orderNodeResultTypes) {
        this.orderNodeResultTypes = orderNodeResultTypes;
    }

    public List<OrderNodeRouteDTO> getOrderNodeRoutes() {
        return orderNodeRoutes;
    }

    public void setOrderNodeRoutes(List<OrderNodeRouteDTO> orderNodeRoutes) {
        this.orderNodeRoutes = orderNodeRoutes;
    }

    public List<OrderNodeFieldValueDTO> getOrderNodeFieldValues() {
        return orderNodeFieldValues;
    }

    public void setOrderNodeFieldValues(List<OrderNodeFieldValueDTO> orderNodeFieldValues) {
        this.orderNodeFieldValues = orderNodeFieldValues;
    }
}
