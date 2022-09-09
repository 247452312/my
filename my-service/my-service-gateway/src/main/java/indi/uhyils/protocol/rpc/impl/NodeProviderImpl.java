package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.NodeDTO;
import indi.uhyils.protocol.rpc.NodeProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 转换节点表(Node)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@RpcService
public class NodeProviderImpl extends BaseDefaultProvider<NodeDTO> implements NodeProvider {


    @Autowired
    private NodeService service;


    @Override
    protected BaseDoService<NodeDTO> getService() {
        return service;
    }

}

