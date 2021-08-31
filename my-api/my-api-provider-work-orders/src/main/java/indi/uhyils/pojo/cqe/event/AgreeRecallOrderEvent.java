package indi.uhyils.pojo.cqe.event;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月17日 18时25分
 */
public class AgreeRecallOrderEvent extends AbstractEvent {

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
