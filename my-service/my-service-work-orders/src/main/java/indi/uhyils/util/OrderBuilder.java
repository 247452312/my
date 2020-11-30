package indi.uhyils.util;

import indi.uhyils.enum_.OrderNodeStatusEnum;
import indi.uhyils.enum_.OrderStatusEnum;
import indi.uhyils.pojo.model.*;

import java.io.Serializable;

/**
 * 工单转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月10日 07时07分
 */
public class OrderBuilder implements Serializable {

    /**
     * 工单基础样例表复制到实例表中去
     *
     * @param baseInfo 工单基础样例表
     * @return 实例
     */
    public static OrderInfoEntity transBaseInfo2Info(OrderBaseInfoEntity baseInfo) {
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        orderInfoEntity.setPriority(baseInfo.getPriority());
        orderInfoEntity.setQueryUserIds(baseInfo.getQueryUserIds());
        orderInfoEntity.setLimitTime(baseInfo.getLimitTime());
        orderInfoEntity.setSon(baseInfo.getSon());
        orderInfoEntity.setMonitorUserId(baseInfo.getMonitorUserId());
        orderInfoEntity.setName(baseInfo.getName());
        orderInfoEntity.setDesc(baseInfo.getDesc());
        orderInfoEntity.setStatus(OrderStatusEnum.START.getCode());
        return orderInfoEntity;
    }

    public static OrderNodeEntity transBaseNode2Node(OrderBaseNodeEntity node, String infoId) {
        OrderNodeEntity nodeEntity = new OrderNodeEntity();
        nodeEntity.setSaveApiId(node.getSaveApiId());
        nodeEntity.setInitApiId(node.getInitApiId());
        nodeEntity.setRunType(node.getRunType());
        nodeEntity.setType(node.getType());
        // 默认开始时是未开始的状态
        nodeEntity.setStatus(OrderNodeStatusEnum.WAIT_STATUS.getCode());
        nodeEntity.setSync(node.getSync());
        nodeEntity.setLimitTime(node.getLimitTime());
        nodeEntity.setNoticeUserId(node.getNoticeUserId());
        nodeEntity.setBaseInfoId(infoId);
        nodeEntity.setRunDealUserId(node.getRunDealUserId());
        nodeEntity.setName(node.getName());
        nodeEntity.setRunApiId(node.getRunApiId());
        nodeEntity.setDesc(node.getDesc());
        return nodeEntity;
    }


    public static OrderNodeFieldEntity transBaseField2Field(OrderBaseNodeFieldEntity orderBaseNodeFieldEntity, String nodeId) {
        OrderNodeFieldEntity orderNodeFieldEntity = new OrderNodeFieldEntity();
        orderNodeFieldEntity.setValueType(orderBaseNodeFieldEntity.getValueType());
        orderNodeFieldEntity.setEdit(orderBaseNodeFieldEntity.getEdit());
        orderNodeFieldEntity.setDefaultValue(orderBaseNodeFieldEntity.getDefaultValue());
        orderNodeFieldEntity.setType(orderBaseNodeFieldEntity.getType());
        orderNodeFieldEntity.setEmpty(orderBaseNodeFieldEntity.getEmpty());
        orderNodeFieldEntity.setName(orderBaseNodeFieldEntity.getName());
        orderNodeFieldEntity.setBaseOrderNodeId(nodeId);
        orderNodeFieldEntity.setDesc(orderBaseNodeFieldEntity.getDesc());
        return orderNodeFieldEntity;
    }

    public static OrderNodeResultTypeEntity transBaseResultType2ResultType(OrderBaseNodeResultTypeEntity orderBaseNodeResultTypeEntity, String nodeId) {
        OrderNodeResultTypeEntity orderNodeResultTypeEntity = new OrderNodeResultTypeEntity();
        orderNodeResultTypeEntity.setBaseNodeId(nodeId);
        orderNodeResultTypeEntity.setDealResultName(orderBaseNodeResultTypeEntity.getDealResultName());

        return orderNodeResultTypeEntity;
    }

    public static OrderNodeRouteEntity transBaseRoute2Route(OrderBaseNodeRouteEntity orderBaseNodeRouteEntity, String nodeId) {
        OrderNodeRouteEntity orderNodeRouteEntity = new OrderNodeRouteEntity();
        orderNodeRouteEntity.setPrevNodeId(nodeId);
        orderNodeRouteEntity.setNextNodeId(orderBaseNodeRouteEntity.getNextNodeId());
        return orderNodeRouteEntity;
    }
}
