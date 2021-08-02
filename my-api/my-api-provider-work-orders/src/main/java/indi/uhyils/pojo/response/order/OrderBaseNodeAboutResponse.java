package indi.uhyils.pojo.response.order;

import indi.uhyils.pojo.model.OrderBaseNodeEntity;
import indi.uhyils.pojo.model.OrderBaseNodeFieldEntity;
import indi.uhyils.pojo.model.OrderBaseNodeResultTypeEntity;
import indi.uhyils.pojo.model.OrderBaseNodeRouteEntity;
import java.io.Serializable;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月12日 15时33分
 */
public class OrderBaseNodeAboutResponse implements Serializable {

    /**
     * 基本工单节点
     */
    private OrderBaseNodeEntity orderBaseNode;

    /**
     * 此工单节点的所有属性
     */
    private List<OrderBaseNodeFieldEntity> orderBaseNodeFields;

    /**
     * 此工单节点结果的类型
     */
    private List<OrderBaseNodeResultTypeEntity> orderBaseNodeResultTypes;

    /**
     * 此工单节点的路由
     */
    private List<OrderBaseNodeRouteEntity> orderBaseNodeRoutes;

    public static OrderBaseNodeAboutResponse build(OrderBaseNodeEntity orderBaseNode, List<OrderBaseNodeFieldEntity> orderBaseNodeFields, List<OrderBaseNodeResultTypeEntity> orderBaseNodeResultTypes, List<OrderBaseNodeRouteEntity> orderBaseNodeRoutes) {
        OrderBaseNodeAboutResponse build = new OrderBaseNodeAboutResponse();
        build.setOrderBaseNode(orderBaseNode);
        build.setOrderBaseNodeFields(orderBaseNodeFields);
        build.setOrderBaseNodeResultTypes(orderBaseNodeResultTypes);
        build.setOrderBaseNodeRoutes(orderBaseNodeRoutes);
        return build;
    }

    public OrderBaseNodeEntity getOrderBaseNode() {
        return orderBaseNode;
    }

    public void setOrderBaseNode(OrderBaseNodeEntity orderBaseNode) {
        this.orderBaseNode = orderBaseNode;
    }

    public List<OrderBaseNodeFieldEntity> getOrderBaseNodeFields() {
        return orderBaseNodeFields;
    }

    public void setOrderBaseNodeFields(List<OrderBaseNodeFieldEntity> orderBaseNodeFields) {
        this.orderBaseNodeFields = orderBaseNodeFields;
    }

    public List<OrderBaseNodeResultTypeEntity> getOrderBaseNodeResultTypes() {
        return orderBaseNodeResultTypes;
    }

    public void setOrderBaseNodeResultTypes(List<OrderBaseNodeResultTypeEntity> orderBaseNodeResultTypes) {
        this.orderBaseNodeResultTypes = orderBaseNodeResultTypes;
    }

    public List<OrderBaseNodeRouteEntity> getOrderBaseNodeRoutes() {
        return orderBaseNodeRoutes;
    }

    public void setOrderBaseNodeRoutes(List<OrderBaseNodeRouteEntity> orderBaseNodeRoutes) {
        this.orderBaseNodeRoutes = orderBaseNodeRoutes;
    }
}
