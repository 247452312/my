package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月22日 08时34分
 */
public class ApplyForInterfacePowerCommand extends AbstractCommand {

    private static final long serialVersionUID = -1L;

    /**
     * 消费者id
     */
    private Long consumerId;

    /**
     * 接口id
     */
    private Long interfaceId;

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }
}
