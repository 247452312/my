package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.OrderNodeFieldEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 复制基础工单后返回的信息
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月15日 10时05分
 */
public class InsertOrderResponse implements Serializable {

    /**
     * 工单初始节点需要填写的属性
     */
    private List<OrderNodeFieldEntity> orderNodeField;
    /**
     * 新生成的工单的id
     */
    private String newOrderId;
    /**
     * 工单监管人
     */
    private String monitorUserId;


    /**
     * 每一步的处理人
     */
    private Map<String, String> dealUserIds;

    /**
     * 每一步的抄送人
     */
    private Map<String, String> noticeUserIds;


    public InsertOrderResponse() {
    }

    public InsertOrderResponse(String newOrderId, List<OrderNodeFieldEntity> orderNodeField, String monitorUserId, Map<String, String> dealUserIds, Map<String, String> noticeUserIds) {
        this.newOrderId = newOrderId;
        this.orderNodeField = orderNodeField;
        this.monitorUserId = monitorUserId;
        this.dealUserIds = dealUserIds;
        this.noticeUserIds = noticeUserIds;
    }

    public static InsertOrderResponse build(String newOrderId, List<OrderNodeFieldEntity> orderNodeField, String monitorUserId, Map<String, String> dealUserIds, Map<String, String> noticeUserIds) {
        InsertOrderResponse build = new InsertOrderResponse();
        build.setNewOrderId(newOrderId);
        build.setOrderNodeField(orderNodeField);
        build.setMonitorUserId(monitorUserId);
        build.setDealUserIds(dealUserIds);
        build.setNoticeUserIds(noticeUserIds);
        return build;

    }

    public String getNewOrderId() {
        return newOrderId;
    }

    public void setNewOrderId(String newOrderId) {
        this.newOrderId = newOrderId;
    }

    public List<OrderNodeFieldEntity> getOrderNodeField() {
        return orderNodeField;
    }

    public void setOrderNodeField(List<OrderNodeFieldEntity> orderNodeField) {
        this.orderNodeField = orderNodeField;
    }

    public String getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(String monitorUserId) {
        this.monitorUserId = monitorUserId;
    }

    public Map<String, String> getDealUserIds() {
        return dealUserIds;
    }

    public void setDealUserIds(Map<String, String> dealUserIds) {
        this.dealUserIds = dealUserIds;
    }

    public Map<String, String> getNoticeUserIds() {
        return noticeUserIds;
    }

    public void setNoticeUserIds(Map<String, String> noticeUserIds) {
        this.noticeUserIds = noticeUserIds;
    }
}
