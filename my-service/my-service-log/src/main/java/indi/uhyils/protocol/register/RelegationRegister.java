package indi.uhyils.protocol.register;

import indi.uhyils.pojo.cqe.event.CheckAndAddRelegationEvent;
import indi.uhyils.protocol.register.base.Register;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月27日 09时08分
 */
public interface RelegationRegister extends Register {

    /**
     * 检查并插入接口降级权重表
     *
     * @param event
     */
    void checkAndAddRelegation(CheckAndAddRelegationEvent event);
}
