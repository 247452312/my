package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时30分
 */
public class CommitOrderCommand extends AbstractCommand {

    /**
     * 工单的id
     */
    private Long orderId;

    /**
     * 真实值
     * <real_value表id,对应值>
     */
    private Map<Long, String> value;

    /**
     * 工单监管人
     */
    private Long monitorUserId;


    /**
     * 每一步的处理人
     */
    private Map<Long, Long> dealUserIds;

    /**
     * 每一步的抄送人
     */
    private Map<Long, Long> noticeUserIds;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Map<Long, String> getValue() {
        return value;
    }

    public void setValue(Map<Long, String> value) {
        this.value = value;
    }

    public Long getMonitorUserId() {
        return monitorUserId;
    }

    public void setMonitorUserId(Long monitorUserId) {
        this.monitorUserId = monitorUserId;
    }

    public Map<Long, Long> getDealUserIds() {
        return dealUserIds;
    }

    public void setDealUserIds(Map<Long, Long> dealUserIds) {
        this.dealUserIds = dealUserIds;
    }

    public Map<Long, Long> getNoticeUserIds() {
        return noticeUserIds;
    }

    public void setNoticeUserIds(Map<Long, Long> noticeUserIds) {
        this.noticeUserIds = noticeUserIds;
    }
}
