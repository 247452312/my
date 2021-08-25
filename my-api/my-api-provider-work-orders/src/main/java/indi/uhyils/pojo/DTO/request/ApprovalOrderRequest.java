package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.DTO.request.base.DefaultRequest;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时30分
 */
public class ApprovalOrderRequest extends DefaultRequest {

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
