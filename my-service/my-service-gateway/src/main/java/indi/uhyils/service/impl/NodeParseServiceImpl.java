package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.NodeParseAssembler;
import indi.uhyils.pojo.DO.NodeParseDO;
import indi.uhyils.pojo.DTO.NodeParseDTO;
import indi.uhyils.pojo.entity.NodeParse;
import indi.uhyils.repository.NodeParseRepository;
import indi.uhyils.service.NodeParseService;
import org.springframework.stereotype.Service;

/**
 * 转换节点解析表(NodeParse)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Service
@ReadWriteMark(tables = {"sys_node_parse"})
public class NodeParseServiceImpl extends AbstractDoService<NodeParseDO, NodeParse, NodeParseDTO, NodeParseRepository, NodeParseAssembler> implements NodeParseService {

    public NodeParseServiceImpl(NodeParseAssembler assembler, NodeParseRepository repository) {
        super(assembler, repository);
    }


}
