package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PlatformInternalNodeAssembler;
import indi.uhyils.dao.PlatformInternalNodeDao;
import indi.uhyils.pojo.DO.PlatformInternalNodeDO;
import indi.uhyils.pojo.DTO.PlatformInternalNodeDTO;
import indi.uhyils.pojo.entity.PlatformInternalNode;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 内部节点表(PlatformInternalNode)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Repository
public class PlatformInternalNodeRepositoryImpl extends AbstractRepository<PlatformInternalNode, PlatformInternalNodeDO, PlatformInternalNodeDao, PlatformInternalNodeDTO, PlatformInternalNodeAssembler> implements PlatformInternalNodeRepository {

    protected PlatformInternalNodeRepositoryImpl(PlatformInternalNodeAssembler convert, PlatformInternalNodeDao dao) {
        super(convert, dao);
    }


}
