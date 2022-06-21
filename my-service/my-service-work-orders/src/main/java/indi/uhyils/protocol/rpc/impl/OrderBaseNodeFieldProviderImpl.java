package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderBaseNodeFieldDTO;
import indi.uhyils.protocol.rpc.OrderBaseNodeFieldProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderBaseNodeFieldService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单节点属性样例表(OrderBaseNodeField)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分02秒
 */
@RpcService
public class OrderBaseNodeFieldProviderImpl extends BaseDefaultProvider<OrderBaseNodeFieldDTO> implements OrderBaseNodeFieldProvider {


    @Autowired
    private OrderBaseNodeFieldService service;


    @Override
    protected BaseDoService<OrderBaseNodeFieldDTO> getService() {
        return service;
    }

}

