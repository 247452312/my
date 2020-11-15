package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时30分
 */
public class CommitOrderRequest extends DefaultRequest {

    /**
     * 工单的id
     */
    private String orderId;

    /**
     * 真实值
     * <real_value表id,对应值>
     */
    private Map<String, String> value;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(Map<String, String> value) {
        this.value = value;
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
