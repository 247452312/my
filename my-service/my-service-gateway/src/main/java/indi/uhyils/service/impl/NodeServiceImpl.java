package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.NodeAssembler;
import indi.uhyils.pojo.DO.NodeDO;
import indi.uhyils.pojo.DTO.NodeDTO;
import indi.uhyils.pojo.entity.Node;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.service.NodeService;
import org.springframework.stereotype.Service;

/**
* 转换节点表(Node)表 内部服务实现类
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年08月12日 08时33分
*/
@Service
@ReadWriteMark(tables = {"sys_node"})
public class NodeServiceImpl extends AbstractDoService<NodeDO, Node, NodeDTO, NodeRepository, NodeAssembler> implements NodeService {

    public NodeServiceImpl(NodeAssembler assembler, NodeRepository repository) {
        super(assembler, repository);
    }


}
