package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetByCodeQuery;
import indi.uhyils.pojo.DTO.request.GetByItemArgsQuery;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.base.AddCommand;
import indi.uhyils.pojo.cqe.query.IdQuery;
import indi.uhyils.pojo.cqe.query.base.BaseArgQuery;
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
    public Boolean insertItem(AddCommand<DictItemDTO> request) {
        DictItemDTO dto = request.getDto();
        return service.insertItem(dto);

    }

    @Override
    public List<DictItemDTO> getItemByDictId(IdQuery request) {
        Identifier dictId = new Identifier(request.getId());
        return service.getItemByDictId(dictId);

    }

    @Override
    public Boolean updateItem(ChangeCommand<DictItemDTO> request) {
        DictItemDTO dto = request.getDto();
        BaseArgQuery order = request.getOrder();
        return service.updateItem(dto, order.getArgs());
    }

    @Override
    public Boolean deleteItem(IdCommand request) {
        Identifier dictItemId = new Identifier(request.getId());
        return service.deleteItem(dictItemId);

    }

    @Override
    public Boolean cleanDictItem(IdCommand request) {
        Identifier dictId = new Identifier(request.getId());
        return service.cleanDictItem(dictId);

    }

    @Override
    public DictItemDTO getItemById(IdQuery request) {
        Identifier dictItemId = new Identifier(request.getId());
        return service.getItemById(dictItemId);

    }

    @Override
    public Page<DictItemDTO> getByItemArgs(GetByItemArgsQuery request) {
        Identifier dictId = new Identifier(request.getDictId());
        List<Arg> args = request.getArgs();
        Order order = request.getOrder();
        Limit limit = request.getLimit();
        return service.getByItemArgs(dictId, args, order, limit);

    }

    @Override
    public VersionInfoDTO getVersionInfoResponse(DefaultCQE request) {
        return service.getVersionInfoResponse();

    }

    @Override
    public LastPlanDTO getLastPlanResponse(DefaultCQE request) {
        return service.getLastPlanResponse();

    }

    @Override
    public List<String> getAllMenuIcon(DefaultCQE request) {
        return service.getAllMenuIcon();

    }

    @Override
    public List<DictItemDTO> getByCode(GetByCodeQuery request) {
        Code code = new Code(request.getCode());
        return service.getByCode(code);

    }

    @Override
    public QuickStartDTO getQuickStartResponse(DefaultCQE request) {
        return service.getQuickStartResponse();
    }

    @Override
    protected BaseDoService<DictDTO> getService() {
        return service;
    }
}
