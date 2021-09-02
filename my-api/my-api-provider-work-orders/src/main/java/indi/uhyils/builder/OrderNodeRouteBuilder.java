package indi.uhyils.builder;


import indi.uhyils.pojo.DTO.OrderNodeRouteDTO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月26日 20时15分
 */
public class OrderNodeRouteBuilder {


    public static OrderNodeRouteDTO build(Long prevNodeId, Long resultId, Long nextNodeId) {
        OrderNodeRouteDTO orderNodeRouteEntity = new OrderNodeRouteDTO();
        orderNodeRouteEntity.setPrevNodeId(prevNodeId);
        orderNodeRouteEntity.setResultId(resultId);
        orderNodeRouteEntity.setNextNodeId(nextNodeId);
        return orderNodeRouteEntity;
    }
}
