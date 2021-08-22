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
    public static OrderInfoDO transBaseInfo2Info(OrderBaseInfoDO baseInfo) {
        OrderInfoDO orderInfoEntity = new OrderInfoDO();
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

    public static OrderNodeDO transBaseNode2Node(OrderBaseNodeDO node, Long infoId) {
        OrderNodeDO nodeEntity = new OrderNodeDO();
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


    public static OrderNodeFieldDO transBaseField2Field(OrderBaseNodeFieldDO orderBaseNodeFieldEntity, Long nodeId) {
        OrderNodeFieldDO orderNodeFieldEntity = new OrderNodeFieldDO();
        orderNodeFieldEntity.setValueType(orderBaseNodeFieldEntity.getValueType());
        orderNodeFieldEntity.setEdit(orderBaseNodeFieldEntity.getEdit());
        orderNodeFieldEntity.setDefaultValue(orderBaseNodeFieldEntity.getDefaultValue());
        orderNodeFieldEntity.setType(orderBaseNodeFieldEntity.getType());
        orderNodeFieldEntity.setEmpty(orderBaseNodeFieldEntity.getEmpty());
        orderNodeFieldEntity.setName(orderBaseNodeFieldEntity.getName());
        orderNodeFieldEntity.setBaseOrderNodeId(nodeId);
        orderNodeFieldEntity.setDataSources(orderBaseNodeFieldEntity.getDataSources());
        orderNodeFieldEntity.setRelationId(orderBaseNodeFieldEntity.getRelationId());
        orderNodeFieldEntity.setDesc(orderBaseNodeFieldEntity.getDesc());
        return orderNodeFieldEntity;
    }

    public static OrderNodeResultTypeDO transBaseResultType2ResultType(OrderBaseNodeResultTypeDO orderBaseNodeResultTypeEntity, Long nodeId) {
        OrderNodeResultTypeDO orderNodeResultTypeEntity = new OrderNodeResultTypeDO();
        orderNodeResultTypeEntity.setBaseNodeId(nodeId);
        orderNodeResultTypeEntity.setDealResultName(orderBaseNodeResultTypeEntity.getDealResultName());

        return orderNodeResultTypeEntity;
    }

    public static OrderNodeRouteDO transBaseRoute2Route(OrderBaseNodeRouteDO orderBaseNodeRouteEntity, Long nodeId) {
        OrderNodeRouteDO orderNodeRouteEntity = new OrderNodeRouteDO();
        orderNodeRouteEntity.setPrevNodeId(nodeId);
        orderNodeRouteEntity.setNextNodeId(orderBaseNodeRouteEntity.getNextNodeId());
        return orderNodeRouteEntity;
    }
}
