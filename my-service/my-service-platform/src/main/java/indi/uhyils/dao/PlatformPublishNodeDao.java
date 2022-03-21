package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.PlatformPublishNodeDO;
import indi.uhyils.pojo.cqe.command.HttpInvokeCommand;
import indi.uhyils.pojo.cqe.command.RpcInvokeCommand;
import org.apache.ibatis.annotations.Mapper;


/**
 * 发布节点表(PlatformPublishNode)表 mapper
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
@Mapper
public interface PlatformPublishNodeDao extends DefaultDao<PlatformPublishNodeDO> {

    /**
     * 创建一个rpc请求
     *
     * @param command
     *
     * @return
     */
    PlatformPublishNodeDO createMyRpc(RpcInvokeCommand command);

    /**
     * 创建一个http请求
     *
     * @param command
     *
     * @return
     */
    PlatformPublishNodeDO createHttp(HttpInvokeCommand command);
}
