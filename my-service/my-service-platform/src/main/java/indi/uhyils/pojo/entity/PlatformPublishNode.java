package indi.uhyils.pojo.entity;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.PlatformPublishNodeDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.node.PublishNode;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.repository.PlatformPublishNodeRepository;

/**
 * 发布节点表(PlatformPublishNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public class PlatformPublishNode extends AbstractDoEntity<PlatformPublishNodeDO> implements PublishNode {

    @Default
    public PlatformPublishNode(PlatformPublishNodeDO data) {
        super(data);
    }

    public PlatformPublishNode(Long id) {
        super(id, new PlatformPublishNodeDO());
    }

    @Override
    public JSONArray invoke(PlatformPublishNodeRepository publishNodeRepository, PlatformInternalNodeRepository internalNodeRepository) {
        return null;
    }
}
