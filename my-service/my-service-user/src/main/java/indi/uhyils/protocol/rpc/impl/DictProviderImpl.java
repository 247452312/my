package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetByCodeRequest;
import indi.uhyils.pojo.DTO.request.GetByItemArgsQuery;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
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
        Boolean result = service.insertItem(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<DictItemDTO>> getItemByDictId(IdQuery request) {
        List<DictItemDTO> result = service.getItemByDictId(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Boolean> updateItem(ChangeCommand<DictItemDTO> request) {
        Boolean result = service.updateItem(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Boolean> deleteItem(IdCommand request) {
        Boolean result = service.deleteItem(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Boolean> cleanDictItem(IdCommand request) {
        Boolean result = service.cleanDictItem(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<DictItemDTO> getItemById(IdQuery request) {
        DictItemDTO result = service.getItemById(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<Page<DictItemDTO>> getByItemArgs(GetByItemArgsQuery request) {
        Page<DictItemDTO> result = service.getByItemArgs(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<VersionInfoDTO> getVersionInfoResponse(DefaultCQE request) {
        VersionInfoDTO result = service.getVersionInfoResponse(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<LastPlanDTO> getLastPlanResponse(DefaultCQE request) {
        LastPlanDTO result = service.getLastPlanResponse(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<String>> getAllMenuIcon(DefaultCQE request) {
        List<String> result = service.getAllMenuIcon(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<List<DictItemDTO>> getByCode(GetByCodeRequest request) {
        List<DictItemDTO> result = service.getByCode(request);
        return ServiceResult.buildSuccessResult(result);

    }

    @Override
    public ServiceResult<QuickStartDTO> getQuickStartResponse(DefaultCQE request) {
        QuickStartDTO result = service.getQuickStartResponse(request);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    protected BaseDoService<DictDTO> getService() {
        return service;
    }
}
