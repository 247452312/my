package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderNodeFieldDTO;
import indi.uhyils.protocol.rpc.OrderNodeFieldProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderNodeFieldService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单节点属性样例表(OrderNodeField)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分25秒
 */
@RpcService
public class OrderNodeFieldProviderImpl extends BaseDefaultProvider<OrderNodeFieldDTO> implements OrderNodeFieldProvider {


    @Autowired
    private OrderNodeFieldService service;


    @Override
    protected BaseDoService<OrderNodeFieldDTO> getService() {
        return service;
    }

}

