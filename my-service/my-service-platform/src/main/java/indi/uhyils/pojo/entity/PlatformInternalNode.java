package indi.uhyils.pojo.entity;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.PlatformInternalNodeDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.node.InternalNode;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.repository.PlatformPublishNodeRepository;

/**
 * 内部节点表(PlatformInternalNode)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月11日 09时31分
 */
public class PlatformInternalNode extends AbstractDoEntity<PlatformInternalNodeDO> implements InternalNode {

    @Default
    public PlatformInternalNode(PlatformInternalNodeDO data) {
        super(data);
    }

    public PlatformInternalNode(Long id) {
        super(id, new PlatformInternalNodeDO());
    }

    @Override
    public JSONArray invoke(PlatformPublishNodeRepository publishNodeRepository, PlatformInternalNodeRepository internalNodeRepository) {
        return null;
    }
}
