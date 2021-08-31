package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderApiDTO;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.OrderApiProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderApiService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 节点api表(OrderApi)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分47秒
 */
@RpcService
public class OrderApiProviderImpl extends BaseDefaultProvider<OrderApiDTO> implements OrderApiProvider {


    @Autowired
    private OrderApiService service;


    @Override
    protected BaseDoService<OrderApiDTO> getService() {
        return service;
    }

}

