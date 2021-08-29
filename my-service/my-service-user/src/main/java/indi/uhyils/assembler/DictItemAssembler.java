package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.DictItemDO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.entity.DictItem;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典子项(DictItem)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分40秒
 */
@Assembler
public class DictItemAssembler extends AbstractAssembler<DictItemDO, DictItem, DictItemDTO> {

    @Override
    public DictItem toEntity(DictItemDO dO) {
        return new DictItem(dO);
    }

    @Override
    public DictItem toEntity(DictItemDTO dto) {
        return new DictItem(toDo(dto));
    }

    @Override
    protected Class<DictItemDO> getDoClass() {
        return DictItemDO.class;
    }

    @Override
    protected Class<DictItemDTO> getDtoClass() {
        return DictItemDTO.class;
    }

    public Page<DictItemDTO> toDTO(Page<DictItem> page) {
        List<DictItemDTO> collect = page.getList().stream().map(this::toDTO).collect(Collectors.toList());
        return Page.build(collect, page);
    }
}

