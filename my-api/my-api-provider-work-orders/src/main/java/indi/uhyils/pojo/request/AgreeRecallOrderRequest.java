package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月17日 18时25分
 */
public class AgreeRecallOrderRequest extends DefaultRequest {

    /**
     * 撤回的工单id
     */
    private Long orderId;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
