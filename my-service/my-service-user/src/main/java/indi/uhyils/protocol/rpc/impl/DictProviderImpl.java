package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.request.GetByCodeRequest;
import indi.uhyils.pojo.DTO.request.GetByItemArgsQuery;
import indi.uhyils.pojo.DTO.response.LastPlanResponse;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoResponse;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
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
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
@ReadWriteMark(tables = {"sys_dict"}, cacheType = CacheTypeEnum.ALL_TYPE)
public class DictProviderImpl extends BaseDefaultProvider<DictDTO> implements DictProvider {



    @Autowired
    private DictService service;

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> insertItem(AddCommand<DictItemDTO> request) {
        return service.insertItem(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<List<DictItemDTO>> getItemByDictId(IdQuery request) {
        return service.getItemByDictId(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> updateItem(ChangeCommand<DictItemDTO> request) {
        return service.updateItem(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> deleteItem(IdCommand request) {
        return service.deleteItem(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public ServiceResult<Boolean> cleanDictItem(IdCommand request) {
        return service.cleanDictItem(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<DictItemDTO> getItemById(IdQuery request) {
        return service.getItemById(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<Page<DictItemDTO>> getByItemArgs(GetByItemArgsQuery request) {
        return service.getByItemArgs(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<VersionInfoResponse> getVersionInfoResponse(DefaultCQE request) {
        return service.getVersionInfoResponse(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<LastPlanResponse> getLastPlanResponse(DefaultCQE request) {
        return service.getLastPlanResponse(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public ServiceResult<List<String>> getAllMenuIcon(DefaultCQE request) {
        return service.getAllMenuIcon(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"}, cacheType = CacheTypeEnum.ALL_TYPE)
    public ServiceResult<List<DictItemDTO>> getByCode(GetByCodeRequest request) {
        return service.getByCode(request);

    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict", "sys_dict_item"})
    public ServiceResult<QuickStartDTO> getQuickStartResponse(DefaultCQE request) {
        return service.getQuickStartResponse(request);
    }

    @Override
    protected BaseDoService<DictDTO> getService() {
        return service;
    }
}
