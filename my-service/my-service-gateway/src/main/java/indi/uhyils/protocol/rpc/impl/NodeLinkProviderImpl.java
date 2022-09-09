package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.NodeLinkDTO;
import indi.uhyils.protocol.rpc.NodeLinkProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.NodeLinkService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 中间节点与外部节点关联关系(NodeLink)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@RpcService
public class NodeLinkProviderImpl extends BaseDefaultProvider<NodeLinkDTO> implements NodeLinkProvider {


    @Autowired
    private NodeLinkService service;


    @Override
    protected BaseDoService<NodeLinkDTO> getService() {
        return service;
    }

}

