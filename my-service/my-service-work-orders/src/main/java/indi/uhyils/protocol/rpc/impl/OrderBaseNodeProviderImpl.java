package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderBaseNodeDTO;
import indi.uhyils.protocol.rpc.OrderBaseNodeProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderBaseNodeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单节点样例表(OrderBaseNode)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分58秒
 */
@RpcService
public class OrderBaseNodeProviderImpl extends BaseDefaultProvider<OrderBaseNodeDTO> implements OrderBaseNodeProvider {


    @Autowired
    private OrderBaseNodeService service;


    @Override
    protected BaseDoService<OrderBaseNodeDTO> getService() {
        return service;
    }

}

