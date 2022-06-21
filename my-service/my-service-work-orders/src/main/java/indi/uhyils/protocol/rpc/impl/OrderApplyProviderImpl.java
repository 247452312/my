package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderApplyDTO;
import indi.uhyils.protocol.rpc.OrderApplyProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderApplyService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (OrderApply)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分51秒
 */
@RpcService
public class OrderApplyProviderImpl extends BaseDefaultProvider<OrderApplyDTO> implements OrderApplyProvider {


    @Autowired
    private OrderApplyService service;


    @Override
    protected BaseDoService<OrderApplyDTO> getService() {
        return service;
    }

}

