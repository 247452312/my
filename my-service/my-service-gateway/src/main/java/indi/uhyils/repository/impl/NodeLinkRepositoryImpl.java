package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.NodeLinkAssembler;
import indi.uhyils.dao.NodeLinkDao;
import indi.uhyils.pojo.DO.NodeLinkDO;
import indi.uhyils.pojo.DTO.NodeLinkDTO;
import indi.uhyils.pojo.entity.NodeLink;
import indi.uhyils.repository.NodeLinkRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 中间节点与外部节点关联关系(NodeLink)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Repository
public class NodeLinkRepositoryImpl extends AbstractRepository<NodeLink, NodeLinkDO, NodeLinkDao, NodeLinkDTO, NodeLinkAssembler> implements NodeLinkRepository {

    protected NodeLinkRepositoryImpl(NodeLinkAssembler convert, NodeLinkDao dao) {
        super(convert, dao);
    }


}
