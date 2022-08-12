package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.NodeParseAssembler;
import indi.uhyils.dao.NodeParseDao;
import indi.uhyils.pojo.DO.NodeParseDO;
import indi.uhyils.pojo.DTO.NodeParseDTO;
import indi.uhyils.pojo.entity.NodeParse;
import indi.uhyils.repository.NodeParseRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 转换节点解析表(NodeParse)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Repository
public class NodeParseRepositoryImpl extends AbstractRepository<NodeParse, NodeParseDO, NodeParseDao, NodeParseDTO, NodeParseAssembler> implements NodeParseRepository {

    protected NodeParseRepositoryImpl(NodeParseAssembler convert, NodeParseDao dao) {
        super(convert, dao);
    }


}
