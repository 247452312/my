package indi.uhyils.repository;

import indi.uhyils.pojo.DO.PlatformPublishNodeDO;
import indi.uhyils.pojo.cqe.command.HttpInvokeCommand;
import indi.uhyils.pojo.cqe.command.RpcInvokeCommand;
import indi.uhyils.pojo.entity.PlatformPublishNode;
import indi.uhyils.pojo.entity.node.PublishNode;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 发布节点表(PlatformPublishNode)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public interface PlatformPublishNodeRepository extends BaseEntityRepository<PlatformPublishNodeDO, PlatformPublishNode> {

    /**
     * 创建一个rpc节点
     *
     * @param command
     *
     * @return
     */
    PublishNode createRpc(RpcInvokeCommand command);

    /**
     * 创建一个http节点
     *
     * @param command
     *
     * @return
     */
    PublishNode createHttp(HttpInvokeCommand command);
}
