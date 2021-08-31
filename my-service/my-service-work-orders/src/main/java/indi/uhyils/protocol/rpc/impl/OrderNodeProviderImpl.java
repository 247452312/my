package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderNodeDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.DealOrderNodeCommand;
import indi.uhyils.pojo.cqe.command.FailOrderNodeCommand;
import indi.uhyils.pojo.cqe.command.IncapacityFailOrderNodeCommand;
import indi.uhyils.pojo.DTO.DealOrderNodeDTO;
import indi.uhyils.pojo.cqe.command.IdsCommand;
import indi.uhyils.protocol.rpc.OrderNodeProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderNodeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单节点样例表(OrderNode)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分22秒
 */
@RpcService
public class OrderNodeProviderImpl extends BaseDefaultProvider<OrderNodeDTO> implements OrderNodeProvider {


    @Autowired
    private OrderNodeService service;


    @Override
    protected BaseDoService<OrderNodeDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<Boolean> deleteByIds(IdsCommand request) {
        Boolean result = service.deleteByIds(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> failOrderNode(FailOrderNodeCommand request) {
        Boolean result = service.failOrderNode(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<DealOrderNodeDTO> dealOrderNode(DealOrderNodeCommand request) throws Exception {
        DealOrderNodeDTO result = service.dealOrderNode(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> incapacityFailOrderNode(IncapacityFailOrderNodeCommand request) throws Exception {
        Boolean result = service.incapacityFailOrderNode(request);
        return ServiceResult.buildSuccessResult(result);
    }
}

