package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.PlatformSourceDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.PublishDbCommand;
import indi.uhyils.pojo.cqe.command.PublishHttpCommand;
import indi.uhyils.pojo.cqe.command.PublishRpcCommand;
import indi.uhyils.protocol.rpc.base.DTOProvider;

/**
 * 资源主表(PlatformSource)表 Rpc对外访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public interface PlatformSourceProvider extends DTOProvider<PlatformSourceDTO> {

    /**
     * 发布一个http接口
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> publishHttp(PublishHttpCommand request);

    /**
     * 发布一个my-rpc接口
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> publishRpc(PublishRpcCommand request);

    /**
     * 发布一个DB接口
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> publishDb(PublishDbCommand request);
}
