package indi.uhyils.service.impl;

import indi.uhyils.assembler.DictAssembler;
import indi.uhyils.assembler.DictItemAssembler;
import indi.uhyils.assembler.MenuAssembler;
import indi.uhyils.pojo.DO.DictDO;
import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.MenuDTO;
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
import indi.uhyils.pojo.entity.Dict;
import indi.uhyils.pojo.entity.DictId;
import indi.uhyils.pojo.entity.DictItem;
import indi.uhyils.pojo.entity.DictItemCode;
import indi.uhyils.pojo.entity.DictItemId;
import indi.uhyils.pojo.entity.MenuId;
import indi.uhyils.repository.DictItemRepository;
import indi.uhyils.repository.DictRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.service.DictService;
import indi.uhyils.util.LogUtil;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据字典(Dict)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分38秒
 */
@Service
public class DictServiceImpl extends AbstractDoService<DictDO, Dict, DictDTO, DictRepository, DictAssembler> implements DictService {

    /**
     * 首页版本信息展示 字典code
     */
    private static final String VERSION_CODE = "myVersionCode";

    /**
     * 首页下一步计划展示 字典code
     */
    private static final String LAST_PLAN_CODE = "lastPlan";

    /**
     * 图标的icon-class
     */
    private static final String MENU_ICON_CLASS_CODE = "icon-class";

    /**
     * 首页下一步计划展示 字典code
     */
    private static final String QUICK_START_CODE = "quickStart";

    @Autowired
    private DictItemRepository dictItemRepository;

    @Autowired
    private DictItemAssembler dictItemAssembler;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuAssembler menuAssembler;

    public DictServiceImpl(DictAssembler assembler, DictRepository repository) {
        super(assembler, repository);
    }

    @Override
    public ServiceResult<Boolean> insertItem(AddCommand<DictItemDTO> request) {
        DictItemDTO dto = request.getDto();
        DictItem dictItem = dictItemAssembler.toEntity(dto);
        dictItemRepository.save(dictItem);
        return ServiceResult.buildSuccessResult("插入字典项成功", true);
    }

    @Override
    public ServiceResult<List<DictItemDTO>> getItemByDictId(IdQuery request) {
        DictId dictId = new DictId(request.getId());
        dictId.fillItem(dictItemRepository);
        List<DictItemDTO> collect = dictId.toItem().stream().map(dictItemAssembler::toDTO).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("查询成功", collect);
    }

    @Override
    public ServiceResult<Boolean> updateItem(ChangeCommand<DictItemDTO> request) {
        DictItemDTO dto = request.getDto();
        DictItem dict = dictItemAssembler.toEntity(dto);
        dictItemRepository.save(dict);
        return ServiceResult.buildSuccessResult("修改成功", true);
    }

    @Override
    public ServiceResult<Boolean> deleteItem(IdCommand request) {
        DictItemId dictItemId = new DictItemId(request.getId());
        DictItem item = dictItemId.completion(dictItemRepository);
        dictItemRepository.remove(Collections.singletonList(item));
        return ServiceResult.buildSuccessResult("删除成功", true);
    }

    @Override
    public ServiceResult<Boolean> cleanDictItem(IdCommand request) {
        DictId dictId = new DictId(request.getId());
        dictId.fillItem(dictItemRepository);
        dictId.cleanItem(dictItemRepository);
        return ServiceResult.buildSuccessResult("清理成功", true);
    }

    @Override
    public ServiceResult<DictItemDTO> getItemById(IdQuery request) {
        DictItemId dictItemId = new DictItemId(request.getId());
        DictItem completion = dictItemId.completion(dictItemRepository);
        return ServiceResult.buildSuccessResult("查询成功", dictItemAssembler.toDTO(completion));
    }

    @Override
    public ServiceResult<Page<DictItemDTO>> getByItemArgs(GetByItemArgsQuery request) {
        Page<DictItem> dictItemPage = dictItemRepository.find(request);
        Page<DictItemDTO> result = dictItemAssembler.toDTO(dictItemPage);
        return ServiceResult.buildSuccessResult("查询成功", result);
    }

    @Override
    public ServiceResult<VersionInfoResponse> getVersionInfoResponse(DefaultCQE request) {
        DictItemCode dictItemCode = new DictItemCode(VERSION_CODE);
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        VersionInfoResponse build = VersionInfoResponse.build(items);
        return ServiceResult.buildSuccessResult("查询成功", build);
    }

    @Override
    public ServiceResult<LastPlanResponse> getLastPlanResponse(DefaultCQE request) {
        DictItemCode dictItemCode = new DictItemCode(VERSION_CODE);
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        LastPlanResponse build = LastPlanResponse.build(items);
        return ServiceResult.buildSuccessResult("查询成功", build);
    }

    @Override
    public ServiceResult<List<String>> getAllMenuIcon(DefaultCQE request) {
        DictItemCode dictItemCode = new DictItemCode(VERSION_CODE);
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        List<String> result = items.stream().map(DictItemDTO::getValue).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("查询成功", result);
    }

    @Override
    public ServiceResult<List<DictItemDTO>> getByCode(GetByCodeRequest request) {
        DictItemCode dictItemCode = new DictItemCode(VERSION_CODE);
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        return ServiceResult.buildSuccessResult("查询成功", items);
    }

    @Override
    public ServiceResult<QuickStartDTO> getQuickStartResponse(DefaultCQE request) {
        DictItemCode dictItemCode = new DictItemCode(VERSION_CODE);
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        List<MenuDTO> result = items.stream().map(t -> new MenuId(Long.valueOf(t.getValue()))).map(menuRepository::find).map(t -> menuAssembler.toDTO(t)).collect(Collectors.toList());
        result.stream().filter(t -> !t.getType()).map(MenuDTO::getName).forEach(t -> LogUtil.error("服务字典中快捷入口(" + t + ") 不是叶子结点"));
        QuickStartDTO build = QuickStartDTO.build(result);
        return ServiceResult.buildSuccessResult("查询快捷入口成功", build);
    }
}
