package indi.uhyils.pojo.DTO.response.order;

import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import indi.uhyils.pojo.DO.OrderBaseNodeFieldDO;
import indi.uhyils.pojo.DO.OrderBaseNodeResultTypeDO;
import indi.uhyils.pojo.DO.OrderBaseNodeRouteDO;
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
    private OrderBaseNodeDO orderBaseNode;

    /**
     * 此工单节点的所有属性
     */
    private List<OrderBaseNodeFieldDO> orderBaseNodeFields;

    /**
     * 此工单节点结果的类型
     */
    private List<OrderBaseNodeResultTypeDO> orderBaseNodeResultTypes;

    /**
     * 此工单节点的路由
     */
    private List<OrderBaseNodeRouteDO> orderBaseNodeRoutes;

    public static OrderBaseNodeAboutResponse build(OrderBaseNodeDO orderBaseNode, List<OrderBaseNodeFieldDO> orderBaseNodeFields, List<OrderBaseNodeResultTypeDO> orderBaseNodeResultTypes, List<OrderBaseNodeRouteDO> orderBaseNodeRoutes) {
        OrderBaseNodeAboutResponse build = new OrderBaseNodeAboutResponse();
        build.setOrderBaseNode(orderBaseNode);
        build.setOrderBaseNodeFields(orderBaseNodeFields);
        build.setOrderBaseNodeResultTypes(orderBaseNodeResultTypes);
        build.setOrderBaseNodeRoutes(orderBaseNodeRoutes);
        return build;
    }

    public OrderBaseNodeDO getOrderBaseNode() {
        return orderBaseNode;
    }

    public void setOrderBaseNode(OrderBaseNodeDO orderBaseNode) {
        this.orderBaseNode = orderBaseNode;
    }

    public List<OrderBaseNodeFieldDO> getOrderBaseNodeFields() {
        return orderBaseNodeFields;
    }

    public void setOrderBaseNodeFields(List<OrderBaseNodeFieldDO> orderBaseNodeFields) {
        this.orderBaseNodeFields = orderBaseNodeFields;
    }

    public List<OrderBaseNodeResultTypeDO> getOrderBaseNodeResultTypes() {
        return orderBaseNodeResultTypes;
    }

    public void setOrderBaseNodeResultTypes(List<OrderBaseNodeResultTypeDO> orderBaseNodeResultTypes) {
        this.orderBaseNodeResultTypes = orderBaseNodeResultTypes;
    }

    public List<OrderBaseNodeRouteDO> getOrderBaseNodeRoutes() {
        return orderBaseNodeRoutes;
    }

    public void setOrderBaseNodeRoutes(List<OrderBaseNodeRouteDO> orderBaseNodeRoutes) {
        this.orderBaseNodeRoutes = orderBaseNodeRoutes;
    }
}
