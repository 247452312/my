package indi.uhyils.service;


import indi.uhyils.pojo.DTO.PlatformSourceDTO;
import indi.uhyils.pojo.cqe.command.PublishDbCommand;
import indi.uhyils.pojo.cqe.command.PublishHttpCommand;
import indi.uhyils.pojo.cqe.command.PublishRpcCommand;

/**
 * 资源主表(PlatformSource)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public interface PlatformSourceService extends BaseDoService<PlatformSourceDTO> {

    /**
     * 发布http形式的资源
     *
     * @param request
     *
     * @return
     */
    Boolean publishHttp(PublishHttpCommand request);

    /**
     * 发布RPC形式的资源
     *
     * @param request
     *
     * @return
     */
    Boolean publishRpc(PublishRpcCommand request);

    /**
     * 发布DB形式的资源
     *
     * @param request
     *
     * @return
     */
    Boolean publishDb(PublishDbCommand request);
}
