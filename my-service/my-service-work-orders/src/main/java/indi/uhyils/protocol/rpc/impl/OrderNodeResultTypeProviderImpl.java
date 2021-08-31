package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderNodeResultTypeDTO;
import indi.uhyils.protocol.rpc.OrderNodeResultTypeProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderNodeResultTypeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单节点处理结果样例表(OrderNodeResultType)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分31秒
 */
@RpcService
public class OrderNodeResultTypeProviderImpl extends BaseDefaultProvider<OrderNodeResultTypeDTO> implements OrderNodeResultTypeProvider {


    @Autowired
    private OrderNodeResultTypeService service;


    @Override
    protected BaseDoService<OrderNodeResultTypeDTO> getService() {
        return service;
    }

}

