package indi.uhyils.pojo.cqe.command;

import indi.uhyils.pojo.cqe.command.base.AbstractCommand;
import java.util.Map;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月22日 14时30分
 */
public class InvokeInterfaceCommand extends AbstractCommand {

    private static final long serialVersionUID = -1L;

    /**
     * 接口id
     */
    private Long interfaceId;

    /**
     * 调用方id
     */
    private Long consumerId;

    /**
     * 入参
     */
    private Map<String, Object> param;

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }
}
