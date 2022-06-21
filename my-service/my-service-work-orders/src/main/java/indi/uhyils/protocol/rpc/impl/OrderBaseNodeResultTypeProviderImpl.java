package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderBaseNodeResultTypeDTO;
import indi.uhyils.protocol.rpc.OrderBaseNodeResultTypeProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderBaseNodeResultTypeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单节点处理结果样例表(OrderBaseNodeResultType)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分05秒
 */
@RpcService
public class OrderBaseNodeResultTypeProviderImpl extends BaseDefaultProvider<OrderBaseNodeResultTypeDTO> implements OrderBaseNodeResultTypeProvider {


    @Autowired
    private OrderBaseNodeResultTypeService service;


    @Override
    protected BaseDoService<OrderBaseNodeResultTypeDTO> getService() {
        return service;
    }

}

