package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.OrderBaseInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.InitOrderDTO;
import indi.uhyils.pojo.DTO.GetOneBaseOrderDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.protocol.rpc.OrderBaseInfoProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.OrderBaseInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工单基础信息样例表(OrderBaseInfo)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分55秒
 */
@RpcService
public class OrderBaseInfoProviderImpl extends BaseDefaultProvider<OrderBaseInfoDTO> implements OrderBaseInfoProvider {


    @Autowired
    private OrderBaseInfoService service;


    @Override
    protected BaseDoService<OrderBaseInfoDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<List<OrderBaseInfoDTO>> getAllBaseOrderIdAndName(DefaultCQE request) {
        List<OrderBaseInfoDTO> result = service.getAllBaseOrderIdAndName(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<GetOneBaseOrderDTO> getOneOrder(IdQuery request) {
        GetOneBaseOrderDTO result = service.getOneOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<InitOrderDTO> initOrder(IdCommand request) {
        InitOrderDTO result = service.initOrder(request);
        return ServiceResult.buildSuccessResult(result);
    }
}

