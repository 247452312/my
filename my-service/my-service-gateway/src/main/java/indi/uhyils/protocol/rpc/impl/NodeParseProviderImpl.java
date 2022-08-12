package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.NodeParseDTO;
import indi.uhyils.protocol.rpc.NodeParseProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.NodeParseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 转换节点解析表(NodeParse)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@RpcService
public class NodeParseProviderImpl extends BaseDefaultProvider<NodeParseDTO> implements NodeParseProvider {


    @Autowired
    private NodeParseService service;


    @Override
    protected BaseDoService<NodeParseDTO> getService() {
        return service;
    }

}

