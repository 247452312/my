package indi.uhyils.service;


import indi.uhyils.pojo.DTO.PlatformPublishNodeDTO;
import indi.uhyils.pojo.cqe.command.HttpInvokeCommand;
import indi.uhyils.pojo.cqe.command.RpcInvokeCommand;
import indi.uhyils.pojo.response.InvokeResponse;

/**
 * 发布节点表(PlatformPublishNode)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public interface PlatformPublishNodeService extends BaseDoService<PlatformPublishNodeDTO> {

    /**
     * 执行sql
     *
     * @param sql
     *
     * @return
     */
    InvokeResponse mysqlInvoke(String sql);

    /**
     * 执行rpc
     *
     * @param command
     *
     * @return
     */
    InvokeResponse rpcInvoke(RpcInvokeCommand command);

    /**
     * 执行http
     *
     * @param command
     *
     * @return
     */
    InvokeResponse httpInvoke(HttpInvokeCommand command);
}
