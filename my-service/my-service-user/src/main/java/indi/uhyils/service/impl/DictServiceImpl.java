package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.DictAssembler;
import indi.uhyils.assembler.DictItemAssembler;
import indi.uhyils.assembler.MenuAssembler;
import indi.uhyils.enum_.CacheTypeEnum;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.DictDO;
import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.base.Page;
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
import indi.uhyils.pojo.entity.Dict;
import indi.uhyils.pojo.entity.DictItem;
import indi.uhyils.pojo.entity.Menu;
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
@ReadWriteMark(tables = {"sys_dict"}, cacheType = CacheTypeEnum.ALL_TYPE)
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
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public Boolean insertItem(AddCommand<DictItemDTO> request) {
        DictItemDTO dto = request.getDto();
        DictItem dictItem = dictItemAssembler.toEntity(dto);
        dictItemRepository.save(dictItem);
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public List<DictItemDTO> getItemByDictId(IdQuery request) {
        Dict dictId = new Dict(request.getId());
        dictId.fillItem(dictItemRepository);
        return dictId.toItem().stream().map(dictItemAssembler::toDTO).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public Boolean updateItem(ChangeCommand<DictItemDTO> request) {
        DictItemDTO dto = request.getDto();
        DictItem dict = dictItemAssembler.toEntity(dto);
        dictItemRepository.save(dict);
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public Boolean deleteItem(IdCommand request) {
        DictItem dictItemId = new DictItem(request.getId());
        dictItemId.completion(dictItemRepository);
        dictItemRepository.remove(Collections.singletonList(dictItemId));
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public Boolean cleanDictItem(IdCommand request) {
        Dict dictId = new Dict(request.getId());
        dictId.fillItem(dictItemRepository);
        dictId.cleanItem(dictItemRepository);
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public DictItemDTO getItemById(IdQuery request) {
        DictItem dictItemId = new DictItem(request.getId());
        dictItemId.completion(dictItemRepository);
        return dictItemAssembler.toDTO(dictItemId);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public Page<DictItemDTO> getByItemArgs(GetByItemArgsQuery request) {
        Page<DictItem> dictItemPage = dictItemRepository.find(request);
        return dictItemAssembler.toDTO(dictItemPage);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public VersionInfoDTO getVersionInfoResponse(DefaultCQE request) {
        Dict dictItemCode = new Dict(VERSION_CODE);
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        return VersionInfoDTO.build(items);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public LastPlanDTO getLastPlanResponse(DefaultCQE request) {
        Dict dictItemCode = new Dict(LAST_PLAN_CODE);
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        return LastPlanDTO.build(items);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public List<String> getAllMenuIcon(DefaultCQE request) {
        Dict dictItemCode = new Dict(MENU_ICON_CLASS_CODE);
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        return items.stream().map(DictItemDTO::getValue).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"}, cacheType = CacheTypeEnum.ALL_TYPE)
    public List<DictItemDTO> getByCode(GetByCodeRequest request) {
        Dict dictItemCode = new Dict(request.getCode());
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        return item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict", "sys_dict_item"})
    public QuickStartDTO getQuickStartResponse(DefaultCQE request) {
        Dict dictItemCode = new Dict(QUICK_START_CODE);
        List<DictItem> item = dictItemCode.findItem(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        List<MenuDTO> result = items.stream().map(t -> new Menu(Long.valueOf(t.getValue()))).map(menuRepository::find).map(t -> menuAssembler.toDTO(t)).collect(Collectors.toList());
        result.stream().filter(t -> !t.getType()).map(MenuDTO::getName).forEach(t -> LogUtil.error("服务字典中快捷入口(" + t + ") 不是叶子结点"));
        return QuickStartDTO.build(result);
    }
}
