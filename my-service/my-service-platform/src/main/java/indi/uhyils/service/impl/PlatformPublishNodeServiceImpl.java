package indi.uhyils.service.impl;

import com.alibaba.fastjson.JSONArray;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PlatformPublishNodeAssembler;
import indi.uhyils.pojo.DO.PlatformPublishNodeDO;
import indi.uhyils.pojo.DTO.PlatformPublishNodeDTO;
import indi.uhyils.pojo.cqe.command.HttpInvokeCommand;
import indi.uhyils.pojo.cqe.command.RpcInvokeCommand;
import indi.uhyils.pojo.entity.PlatformPublishNode;
import indi.uhyils.pojo.entity.VirtualNodeImpl;
import indi.uhyils.pojo.entity.node.Node;
import indi.uhyils.pojo.response.InvokeResponse;
import indi.uhyils.repository.PlatformInternalNodeRepository;
import indi.uhyils.repository.PlatformPublishNodeRepository;
import indi.uhyils.service.PlatformPublishNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发布节点表(PlatformPublishNode)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 09时31分
 */
@Service
@ReadWriteMark(tables = {"sys_platform_publish_node"})
public class PlatformPublishNodeServiceImpl extends AbstractDoService<PlatformPublishNodeDO, PlatformPublishNode, PlatformPublishNodeDTO, PlatformPublishNodeRepository, PlatformPublishNodeAssembler> implements PlatformPublishNodeService {

    @Autowired
    private PlatformInternalNodeRepository internalNodeRepository;

    public PlatformPublishNodeServiceImpl(PlatformPublishNodeAssembler assembler, PlatformPublishNodeRepository repository) {
        super(assembler, repository);
    }

    @Override
    public InvokeResponse mysqlInvoke(String sql) {
        Node virtualNode = new VirtualNodeImpl(sql);
        JSONArray invoke = virtualNode.invoke(rep, internalNodeRepository);
        return InvokeResponse.build(invoke);
    }

    @Override
    public InvokeResponse rpcInvoke(RpcInvokeCommand command) {
        Node publishNode = rep.findRpcPublishNode(command);
        return InvokeResponse.build(publishNode.invoke(rep, internalNodeRepository));
    }

    @Override
    public InvokeResponse httpInvoke(HttpInvokeCommand command) {
        Node publishNode = rep.findHttpPublishNode(command);
        return InvokeResponse.build(publishNode.invoke(rep, internalNodeRepository));
    }
}
