package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderNodeFieldValueDTO;
import indi.uhyils.protocol.rpc.OrderNodeFieldValueProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderNodeFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分28秒
 */
@RpcService
public class OrderNodeFieldValueProviderImpl extends BaseDefaultProvider<OrderNodeFieldValueDTO> implements OrderNodeFieldValueProvider {


    @Autowired
    private OrderNodeFieldValueService service;


    @Override
    protected BaseDoService<OrderNodeFieldValueDTO> getService() {
        return service;
    }

}

