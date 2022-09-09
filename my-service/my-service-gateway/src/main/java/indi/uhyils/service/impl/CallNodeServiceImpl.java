package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.CallNodeAssembler;
import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.pojo.DTO.CallNodeDTO;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.entity.CallNode;
import indi.uhyils.repository.CallNodeRepository;
import indi.uhyils.repository.NodeRepository;
import indi.uhyils.repository.ProviderInterfaceRepository;
import indi.uhyils.service.CallNodeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Service
@ReadWriteMark(tables = {"sys_call_node"})
public class CallNodeServiceImpl extends AbstractDoService<CallNodeDO, CallNode, CallNodeDTO, CallNodeRepository, CallNodeAssembler> implements CallNodeService {


    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private ProviderInterfaceRepository providerInterfaceRepository;

    public CallNodeServiceImpl(CallNodeAssembler assembler, CallNodeRepository repository) {
        super(assembler, repository);
    }


    @Override
    public List<CallNodeDTO> queryWithAllNode(List<Arg> args) {
        final List<CallNode> callNodes = rep.findNoPage(args);
        for (CallNode callNode : callNodes) {
            // 向下填充
            callNode.fillSubNode(nodeRepository, providerInterfaceRepository);
        }
        return assem.listEntityToDTO(callNodes);
    }
}
