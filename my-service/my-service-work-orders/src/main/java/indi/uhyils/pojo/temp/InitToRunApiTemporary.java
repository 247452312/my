package indi.uhyils.pojo.temp;

import indi.uhyils.pojo.dto.ApiDealDto;
import indi.uhyils.pojo.model.OrderNodeDO;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月22日 17时14分
 */
public class InitToRunApiTemporary implements Serializable {

    /**
     * 工单节点
     */
    private OrderNodeDO orderNode;

    /**
     * 上一个工单节点
     */
    private OrderNodeDO pervOrderNode;

    /**
     * 初始化节点执行的结果
     */
    private ApiDealDto apiDealDto;

    public static InitToRunApiTemporary build(OrderNodeDO orderNode, OrderNodeDO pervOrderNode, ApiDealDto apiDealDto) {
        InitToRunApiTemporary build = new InitToRunApiTemporary();
        build.orderNode = orderNode;
        build.pervOrderNode = pervOrderNode;
        build.apiDealDto = apiDealDto;
        return build;
    }

    public OrderNodeDO getOrderNode() {
        return orderNode;
    }

    public void setOrderNode(OrderNodeDO orderNode) {
        this.orderNode = orderNode;
    }

    public OrderNodeDO getPervOrderNode() {
        return pervOrderNode;
    }

    public void setPervOrderNode(OrderNodeDO pervOrderNode) {
        this.pervOrderNode = pervOrderNode;
    }

    public ApiDealDto getApiDealDto() {
        return apiDealDto;
    }

    public void setApiDealDto(ApiDealDto apiDealDto) {
        this.apiDealDto = apiDealDto;
    }
}
