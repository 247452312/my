package indi.uhyils.builder;

import indi.uhyils.pojo.model.OrderNodeRouteEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月26日 20时15分
 */
public class OrderNodeRouteBuilder {


    public static OrderNodeRouteEntity build(Long prevNodeId, Long resultId, Long nextNodeId) {
        OrderNodeRouteEntity orderNodeRouteEntity = new OrderNodeRouteEntity();
        orderNodeRouteEntity.setPrevNodeId(prevNodeId);
        orderNodeRouteEntity.setResultId(resultId);
        orderNodeRouteEntity.setNextNodeId(nextNodeId);
        return orderNodeRouteEntity;
    }
}
