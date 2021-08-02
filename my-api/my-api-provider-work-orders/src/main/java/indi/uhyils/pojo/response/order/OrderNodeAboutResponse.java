package indi.uhyils.pojo.response.order;


import indi.uhyils.pojo.model.OrderNodeEntity;
import indi.uhyils.pojo.model.OrderNodeFieldEntity;
import indi.uhyils.pojo.model.OrderNodeFieldValueEntity;
import indi.uhyils.pojo.model.OrderNodeResultTypeEntity;
import indi.uhyils.pojo.model.OrderNodeRouteEntity;
import java.io.Serializable;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月12日 15时33分
 */
public class OrderNodeAboutResponse implements Serializable {

    /**
     * 基本工单节点
     */
    private OrderNodeEntity orderNode;

    /**
     * 此工单节点的所有属性
     */
    private List<OrderNodeFieldEntity> orderNodeFields;

    /**
     * 此工单节点结果的类型
     */
    private List<OrderNodeResultTypeEntity> orderNodeResultTypes;

    /**
     * 此工单节点的路由
     */
    private List<OrderNodeRouteEntity> orderNodeRoutes;

    /**
     * 此节点的所有属性填写的值
     */
    private List<OrderNodeFieldValueEntity> orderNodeFieldValues;

    public static OrderNodeAboutResponse build(OrderNodeEntity orderNode, List<OrderNodeFieldEntity> orderNodeFields, List<OrderNodeResultTypeEntity> orderNodeResultTypes, List<OrderNodeRouteEntity> orderNodeRoutes, List<OrderNodeFieldValueEntity> orderNodeFieldValues) {
        OrderNodeAboutResponse build = new OrderNodeAboutResponse();
        build.setOrderNode(orderNode);
        build.setOrderNodeFields(orderNodeFields);
        build.setOrderNodeResultTypes(orderNodeResultTypes);
        build.setOrderNodeRoutes(orderNodeRoutes);
        build.setOrderNodeFieldValues(orderNodeFieldValues);
        return build;
    }

    public OrderNodeEntity getOrderNode() {
        return orderNode;
    }

    public void setOrderNode(OrderNodeEntity orderNode) {
        this.orderNode = orderNode;
    }

    public List<OrderNodeFieldEntity> getOrderNodeFields() {
        return orderNodeFields;
    }

    public void setOrderNodeFields(List<OrderNodeFieldEntity> orderNodeFields) {
        this.orderNodeFields = orderNodeFields;
    }

    public List<OrderNodeResultTypeEntity> getOrderNodeResultTypes() {
        return orderNodeResultTypes;
    }

    public void setOrderNodeResultTypes(List<OrderNodeResultTypeEntity> orderNodeResultTypes) {
        this.orderNodeResultTypes = orderNodeResultTypes;
    }

    public List<OrderNodeRouteEntity> getOrderNodeRoutes() {
        return orderNodeRoutes;
    }

    public void setOrderNodeRoutes(List<OrderNodeRouteEntity> orderNodeRoutes) {
        this.orderNodeRoutes = orderNodeRoutes;
    }

    public List<OrderNodeFieldValueEntity> getOrderNodeFieldValues() {
        return orderNodeFieldValues;
    }

    public void setOrderNodeFieldValues(List<OrderNodeFieldValueEntity> orderNodeFieldValues) {
        this.orderNodeFieldValues = orderNodeFieldValues;
    }
}
