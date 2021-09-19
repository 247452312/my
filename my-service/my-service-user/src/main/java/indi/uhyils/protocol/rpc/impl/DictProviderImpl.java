package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetByCodeQuery;
import indi.uhyils.pojo.DTO.request.GetByItemArgsQuery;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.base.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.base.BaseArgQuery;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import indi.uhyils.pojo.entity.type.Code;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.protocol.rpc.DictProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.DictService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
public class DictProviderImpl extends BaseDefaultProvider<DictDTO> implements DictProvider {


    @Autowired
    private DictService service;

    @Override
    public ServiceResult<Boolean> insertItem(AddCommand<DictItemDTO> request) {
        DictItemDTO dto = request.getDto();
        Boolean result = service.insertItem(dto);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<DictItemDTO>> getItemByDictId(IdQuery request) {
        Identifier dictId = new Identifier(request.getId());
        List<DictItemDTO> result = service.getItemByDictId(dictId);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Boolean> updateItem(ChangeCommand<DictItemDTO> request) {
        DictItemDTO dto = request.getDto();
        BaseArgQuery order = request.getOrder();
        Boolean result = service.updateItem(dto, order.getArgs());
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<Boolean> deleteItem(IdCommand request) {
        Identifier dictItemId = new Identifier(request.getId());
        Boolean result = service.deleteItem(dictItemId);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Boolean> cleanDictItem(IdCommand request) {
        Identifier dictId = new Identifier(request.getId());
        Boolean result = service.cleanDictItem(dictId);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<DictItemDTO> getItemById(IdQuery request) {
        Identifier dictItemId = new Identifier(request.getId());
        DictItemDTO result = service.getItemById(dictItemId);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Page<DictItemDTO>> getByItemArgs(GetByItemArgsQuery request) {
        Identifier dictId = new Identifier(request.getDictId());
        List<Arg> args = request.getArgs();
        Order order = request.getOrder();
        Limit limit = request.getLimit();
        Page<DictItemDTO> result = service.getByItemArgs(dictId, args, order, limit);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<VersionInfoDTO> getVersionInfoResponse(DefaultCQE request) {
        VersionInfoDTO result = service.getVersionInfoResponse();
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<LastPlanDTO> getLastPlanResponse(DefaultCQE request) {
        LastPlanDTO result = service.getLastPlanResponse();
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<String>> getAllMenuIcon(DefaultCQE request) {
        List<String> result = service.getAllMenuIcon();
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<DictItemDTO>> getByCode(GetByCodeQuery request) {
        Code code = new Code(request.getCode());
        List<DictItemDTO> result = service.getByCode(code);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<QuickStartDTO> getQuickStartResponse(DefaultCQE request) {
        QuickStartDTO result = service.getQuickStartResponse();
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    protected BaseDoService<DictDTO> getService() {
        return service;
    }
}
