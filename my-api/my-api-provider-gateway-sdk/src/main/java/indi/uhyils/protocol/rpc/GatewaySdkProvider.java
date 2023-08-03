package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.cqe.InvokeCommand;
import java.util.List;
import java.util.Map;

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
    List<Map<String, Object>> invokeRpc(InvokeCommand command);
}
