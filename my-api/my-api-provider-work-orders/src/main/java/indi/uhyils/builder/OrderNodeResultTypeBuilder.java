package indi.uhyils.builder;

import indi.uhyils.pojo.model.OrderNodeResultTypeDO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月26日 20时07分
 */
public class OrderNodeResultTypeBuilder {

    /**
     * 创建一个新的节点结果
     *
     * @param orderNodeId
     * @param msg
     *
     * @return
     */
    public static OrderNodeResultTypeDO build(Long orderNodeId, String msg) {
        OrderNodeResultTypeDO orderNodeResultTypeEntity = new OrderNodeResultTypeDO();
        orderNodeResultTypeEntity.setBaseNodeId(orderNodeId);
        orderNodeResultTypeEntity.setDealResultName(msg);
        return orderNodeResultTypeEntity;
    }
}
