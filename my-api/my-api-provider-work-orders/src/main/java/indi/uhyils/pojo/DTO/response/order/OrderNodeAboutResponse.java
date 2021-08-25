package indi.uhyils.pojo.DTO.response.order;


import indi.uhyils.pojo.DO.OrderNodeDO;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.DO.OrderNodeRouteDO;
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
    private OrderNodeDO orderNode;

    /**
     * 此工单节点的所有属性
     */
    private List<OrderNodeFieldDO> orderNodeFields;

    /**
     * 此工单节点结果的类型
     */
    private List<OrderNodeResultTypeDO> orderNodeResultTypes;

    /**
     * 此工单节点的路由
     */
    private List<OrderNodeRouteDO> orderNodeRoutes;

    /**
     * 此节点的所有属性填写的值
     */
    private List<OrderNodeFieldValueDO> orderNodeFieldValues;

    public static OrderNodeAboutResponse build(OrderNodeDO orderNode, List<OrderNodeFieldDO> orderNodeFields, List<OrderNodeResultTypeDO> orderNodeResultTypes, List<OrderNodeRouteDO> orderNodeRoutes, List<OrderNodeFieldValueDO> orderNodeFieldValues) {
        OrderNodeAboutResponse build = new OrderNodeAboutResponse();
        build.setOrderNode(orderNode);
        build.setOrderNodeFields(orderNodeFields);
        build.setOrderNodeResultTypes(orderNodeResultTypes);
        build.setOrderNodeRoutes(orderNodeRoutes);
        build.setOrderNodeFieldValues(orderNodeFieldValues);
        return build;
    }

    public OrderNodeDO getOrderNode() {
        return orderNode;
    }

    public void setOrderNode(OrderNodeDO orderNode) {
        this.orderNode = orderNode;
    }

    public List<OrderNodeFieldDO> getOrderNodeFields() {
        return orderNodeFields;
    }

    public void setOrderNodeFields(List<OrderNodeFieldDO> orderNodeFields) {
        this.orderNodeFields = orderNodeFields;
    }

    public List<OrderNodeResultTypeDO> getOrderNodeResultTypes() {
        return orderNodeResultTypes;
    }

    public void setOrderNodeResultTypes(List<OrderNodeResultTypeDO> orderNodeResultTypes) {
        this.orderNodeResultTypes = orderNodeResultTypes;
    }

    public List<OrderNodeRouteDO> getOrderNodeRoutes() {
        return orderNodeRoutes;
    }

    public void setOrderNodeRoutes(List<OrderNodeRouteDO> orderNodeRoutes) {
        this.orderNodeRoutes = orderNodeRoutes;
    }

    public List<OrderNodeFieldValueDO> getOrderNodeFieldValues() {
        return orderNodeFieldValues;
    }

    public void setOrderNodeFieldValues(List<OrderNodeFieldValueDO> orderNodeFieldValues) {
        this.orderNodeFieldValues = orderNodeFieldValues;
    }
}
