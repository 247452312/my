package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.PlatformPublishNodeDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.HttpInvokeCommand;
import indi.uhyils.pojo.cqe.command.MysqlInvokeCommand;
import indi.uhyils.pojo.cqe.command.RpcInvokeCommand;
import indi.uhyils.pojo.response.InvokeResponse;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 发布节点表(PlatformPublishNode)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public interface PlatformPublishNodeProvider extends DTOProvider<PlatformPublishNodeDTO> {

    /**
     * 执行sql
     *
     * @param command
     *
     * @return
     */
    ServiceResult<InvokeResponse> mysqlInvoke(MysqlInvokeCommand command);

    /**
     * 执行rpc请求
     *
     * @param command
     *
     * @return
     */
    ServiceResult<InvokeResponse> rpcInvoke(RpcInvokeCommand command);

    /**
     * 执行http请求
     *
     * @param command
     *
     * @return
     */
    ServiceResult<InvokeResponse> httpInvoke(HttpInvokeCommand command);


}
