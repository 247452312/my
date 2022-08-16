package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.cqe.InvokeCommand;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月12日 08时55分
 */
public interface GatewaySdkProvider {

    /**
     * 执行远程请求
     *
     * @param command
     *
     * @return
     */
    Object invokeRpc(InvokeCommand command);
}
