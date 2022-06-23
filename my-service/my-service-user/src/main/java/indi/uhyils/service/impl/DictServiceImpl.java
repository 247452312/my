package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.DictAssembler;
import indi.uhyils.assembler.DictItemAssembler;
import indi.uhyils.assembler.MenuAssembler;
import indi.uhyils.enums.CacheTypeEnum;
import indi.uhyils.enums.ReadWriteTypeEnum;
import indi.uhyils.pojo.DO.DictDO;
import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import indi.uhyils.pojo.entity.Dict;
import indi.uhyils.pojo.entity.DictItem;
import indi.uhyils.pojo.entity.Menu;
import indi.uhyils.pojo.entity.type.Code;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DictItemRepository;
import indi.uhyils.repository.DictRepository;
import indi.uhyils.repository.MenuRepository;
import indi.uhyils.service.DictService;
import indi.uhyils.util.LogUtil;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    public Boolean insertItem(DictItemDTO dto) {
        DictItem dictItem = dictItemAssembler.toEntity(dto);
        dictItemRepository.save(dictItem);
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public List<DictItemDTO> getItemByDictId(Identifier dictId) {
        Dict dict = new Dict(dictId.getId());
        dict.fillItem(dictItemRepository);
        return dict.toItem().stream().map(dictItemAssembler::toDTO).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public Boolean updateItem(DictItemDTO dto, List<Arg> args) {
        DictItem dict = dictItemAssembler.toEntity(dto);
        dictItemRepository.change(dict, args);
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public Boolean deleteItem(Identifier dictItemId) {
        DictItem dictItem = new DictItem(dictItemId.getId());
        dictItem.completion(dictItemRepository);
        dictItemRepository.remove(Collections.singletonList(dictItem));
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict_item"})
    public Boolean cleanDictItem(Identifier dictId) {
        Dict dict = new Dict(dictId.getId());
        dict.fillItem(dictItemRepository);
        dict.cleanItem(dictItemRepository);
        return true;
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public DictItemDTO getItemById(Identifier dictItemId) {
        DictItem dictItem = new DictItem(dictItemId.getId());
        dictItem.completion(dictItemRepository);
        return dictItemAssembler.toDTO(dictItem);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public Page<DictItemDTO> getByItemArgs(Identifier dictId, List<Arg> args, Order order, Limit limit) {
        Page<DictItem> dictItemPage = dictItemRepository.find(dictId, args, order, limit);
        return dictItemAssembler.toDTO(dictItemPage);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public VersionInfoDTO getVersionInfoResponse() {
        Dict dictItemCode = new Dict(VERSION_CODE);
        List<DictItem> item = dictItemCode.findItemByCode(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        return VersionInfoDTO.build(items);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public LastPlanDTO getLastPlanResponse() {
        Dict dictItemCode = new Dict(LAST_PLAN_CODE);
        List<DictItem> item = dictItemCode.findItemByCode(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        return LastPlanDTO.build(items);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"})
    public List<String> getAllMenuIcon() {
        Dict dictItemCode = new Dict(MENU_ICON_CLASS_CODE);
        List<DictItem> item = dictItemCode.findItemByCode(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        return items.stream().map(DictItemDTO::getValue).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.READ, tables = {"sys_dict_item"}, cacheType = CacheTypeEnum.ALL_TYPE)
    public List<DictItemDTO> getByCode(Code code) {
        Dict dictItemCode = new Dict(code.getCode());
        List<DictItem> item = dictItemCode.findItemByCode(dictItemRepository);
        return item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE, tables = {"sys_dict", "sys_dict_item"})
    public QuickStartDTO getQuickStartResponse() {
        Dict dictItemCode = new Dict(QUICK_START_CODE);
        List<DictItem> item = dictItemCode.findItemByCode(dictItemRepository);
        List<DictItemDTO> items = item.stream().map(t -> dictItemAssembler.toDTO(t)).collect(Collectors.toList());
        List<MenuDTO> result = items.stream().map(t -> new Menu(Long.valueOf(t.getValue()))).map(menuRepository::find).map(t -> menuAssembler.toDTO(t)).collect(Collectors.toList());
        result.stream().filter(t -> !t.getType()).map(MenuDTO::getName).forEach(t -> LogUtil.error("服务字典中快捷入口(" + t + ") 不是叶子结点"));
        return QuickStartDTO.build(result);
    }
}
