package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PlatformPublishNodeAssembler;
import indi.uhyils.dao.PlatformPublishNodeDao;
import indi.uhyils.pojo.DO.PlatformPublishNodeDO;
import indi.uhyils.pojo.DTO.PlatformPublishNodeDTO;
import indi.uhyils.pojo.entity.PlatformPublishNode;
import indi.uhyils.repository.PlatformPublishNodeRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 发布节点表(PlatformPublishNode)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Repository
public class PlatformPublishNodeRepositoryImpl extends AbstractRepository<PlatformPublishNode, PlatformPublishNodeDO, PlatformPublishNodeDao, PlatformPublishNodeDTO, PlatformPublishNodeAssembler> implements PlatformPublishNodeRepository {

    protected PlatformPublishNodeRepositoryImpl(PlatformPublishNodeAssembler convert, PlatformPublishNodeDao dao) {
        super(convert, dao);
    }


}
