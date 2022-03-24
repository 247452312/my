package indi.uhyils.pojo.entity.node;

import indi.uhyils.pojo.DTO.NodeInvokeResult;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.repository.PlatformPublishNodeRepository;

/**
 * 执行节点
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月21日 09时48分
 */
public interface Node {

    /**
     * 执行节点,得到结果
     *
     * @return
     */
    NodeInvokeResult invoke(PlatformPublishNodeRepository publishNodeRepository, PlatformInternalNodeRepository internalNodeRepository);


}
