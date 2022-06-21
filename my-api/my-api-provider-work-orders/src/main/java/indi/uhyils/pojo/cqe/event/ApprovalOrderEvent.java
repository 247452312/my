package indi.uhyils.pojo.cqe.event;


import indi.uhyils.pojo.cqe.event.base.AbstractEvent;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时30分
 */
public class ApprovalOrderEvent extends AbstractEvent {

    /**
     * 申请id
     */
    private Long orderApplyId;


    public Long getOrderApplyId() {
        return orderApplyId;
    }

    public void setOrderApplyId(Long orderApplyId) {
        this.orderApplyId = orderApplyId;
    }
}
