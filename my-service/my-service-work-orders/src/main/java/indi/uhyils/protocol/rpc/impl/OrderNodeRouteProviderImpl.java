package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderNodeRouteDTO;
import indi.uhyils.protocol.rpc.OrderNodeRouteProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderNodeRouteService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 节点间关联路由样例表(OrderNodeRoute)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分35秒
 */
@RpcService
public class OrderNodeRouteProviderImpl extends BaseDefaultProvider<OrderNodeRouteDTO> implements OrderNodeRouteProvider {


    @Autowired
    private OrderNodeRouteService service;


    @Override
    protected BaseDoService<OrderNodeRouteDTO> getService() {
        return service;
    }

}

