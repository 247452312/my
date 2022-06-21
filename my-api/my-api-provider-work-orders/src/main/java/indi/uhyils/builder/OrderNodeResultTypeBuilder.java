package indi.uhyils.builder;


import indi.uhyils.pojo.DTO.OrderNodeResultTypeDTO;

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
    public static OrderNodeResultTypeDTO build(Long orderNodeId, String msg) {
        OrderNodeResultTypeDTO orderNodeResultTypeEntity = new OrderNodeResultTypeDTO();
        orderNodeResultTypeEntity.setBaseNodeId(orderNodeId);
        orderNodeResultTypeEntity.setDealResultName(msg);
        return orderNodeResultTypeEntity;
    }
}
