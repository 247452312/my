package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.NodeAssembler;
import indi.uhyils.dao.NodeDao;
import indi.uhyils.pojo.DO.NodeDO;
import indi.uhyils.pojo.DTO.NodeDTO;
import indi.uhyils.pojo.entity.Node;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
* 转换节点表(Node)表 仓库实现
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年08月12日 08时33分
*/
@Repository
public class NodeRepositoryImpl extends AbstractRepository<Node, NodeDO, NodeDao, NodeDTO, NodeAssembler> implements NodeRepository {

    protected NodeRepositoryImpl(NodeAssembler convert, NodeDao dao) {
        super(convert, dao);
    }


}
