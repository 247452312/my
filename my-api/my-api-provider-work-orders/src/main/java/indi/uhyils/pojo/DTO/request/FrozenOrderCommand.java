package indi.uhyils.pojo.DTO.request;


import indi.uhyils.pojo.cqe.command.AbstractCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时30分
 */
public class FrozenOrderCommand extends AbstractCommand {

    /**
     * 工单id
     */
    private Long orderId;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
