package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.NodeLinkAssembler;
import indi.uhyils.pojo.DO.NodeLinkDO;
import indi.uhyils.pojo.DTO.NodeLinkDTO;
import indi.uhyils.pojo.entity.NodeLink;
import indi.uhyils.repository.NodeLinkRepository;
import indi.uhyils.service.NodeLinkService;
import org.springframework.stereotype.Service;

/**
 * 中间节点与外部节点关联关系(NodeLink)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Service
@ReadWriteMark(tables = {"sys_node_link"})
public class NodeLinkServiceImpl extends AbstractDoService<NodeLinkDO, NodeLink, NodeLinkDTO, NodeLinkRepository, NodeLinkAssembler> implements NodeLinkService {

    public NodeLinkServiceImpl(NodeLinkAssembler assembler, NodeLinkRepository repository) {
        super(assembler, repository);
    }


}
