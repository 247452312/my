package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderBaseNodeRouteDTO;
import indi.uhyils.protocol.rpc.OrderBaseNodeRouteProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderBaseNodeRouteService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 节点间关联路由样例表(OrderBaseNodeRoute)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分09秒
 */
@RpcService
public class OrderBaseNodeRouteProviderImpl extends BaseDefaultProvider<OrderBaseNodeRouteDTO> implements OrderBaseNodeRouteProvider {


    @Autowired
    private OrderBaseNodeRouteService service;


    @Override
    protected BaseDoService<OrderBaseNodeRouteDTO> getService() {
        return service;
    }

}

