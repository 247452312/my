package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PlatformNodeLinkAssembler;
import indi.uhyils.dao.PlatformNodeLinkDao;
import indi.uhyils.pojo.DO.PlatformNodeLinkDO;
import indi.uhyils.pojo.DTO.PlatformNodeLinkDTO;
import indi.uhyils.pojo.entity.PlatformNodeLink;
import indi.uhyils.repository.PlatformNodeLinkRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 节点关联表(PlatformNodeLink)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Repository
public class PlatformNodeLinkRepositoryImpl extends AbstractRepository<PlatformNodeLink, PlatformNodeLinkDO, PlatformNodeLinkDao, PlatformNodeLinkDTO, PlatformNodeLinkAssembler> implements PlatformNodeLinkRepository {

    protected PlatformNodeLinkRepositoryImpl(PlatformNodeLinkAssembler convert, PlatformNodeLinkDao dao) {
        super(convert, dao);
    }


}
