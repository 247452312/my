package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.InitOrderDTO;
import indi.uhyils.pojo.DTO.OrderInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.cqe.command.CommitOrderCommand;
import indi.uhyils.pojo.cqe.command.FrozenOrderCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.RecallOrderCommand;
import indi.uhyils.pojo.cqe.command.RestartOrderCommand;
import indi.uhyils.pojo.cqe.event.AgreeRecallOrderEvent;
import indi.uhyils.pojo.cqe.event.ApprovalOrderEvent;
import indi.uhyils.pojo.cqe.query.GetAllOrderQuery;
import indi.uhyils.protocol.rpc.OrderInfoProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单基础信息样例表(OrderInfo)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分15秒
 */
@RpcService
public class OrderInfoProviderImpl extends BaseDefaultProvider<OrderInfoDTO> implements OrderInfoProvider {


    @Autowired
    private OrderInfoService service;


    @Override
    protected BaseDoService<OrderInfoDTO> getService() {
        return service;
    }


    @Override
    public ServiceResult<InitOrderDTO> initOrder(IdCommand request) {
        InitOrderDTO result = service.initOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<OrderInfoDTO>> getAllOrder(GetAllOrderQuery request) {
        List<OrderInfoDTO> result = service.getAllOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }


    @Override
    public ServiceResult<Boolean> commitOrder(CommitOrderCommand request) {
        Boolean result = service.commitOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> recallOrder(RecallOrderCommand request) {
        Boolean result = service.recallOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> agreeRecallOrder(AgreeRecallOrderEvent request) {
        Boolean result = service.agreeRecallOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> frozenOrder(FrozenOrderCommand request) {
        Boolean result = service.frozenOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> restartOrder(RestartOrderCommand request) {
        Boolean result = service.restartOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> approvalOrder(ApprovalOrderEvent request) {
        Boolean result = service.approvalOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }
}

