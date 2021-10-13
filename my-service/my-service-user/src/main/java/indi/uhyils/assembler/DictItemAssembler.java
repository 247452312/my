package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.DictItemDO;
import indi.uhyils.pojo.DTO.DictItemDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.entity.DictItem;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

/**
 * 数据字典子项(DictItem)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分40秒
 */
@Mapper(componentModel = "spring")
public abstract class DictItemAssembler extends AbstractAssembler<DictItemDO, DictItem, DictItemDTO> {

    public Page<DictItemDTO> toDTO(Page<DictItem> page) {
        List<DictItemDTO> collect = page.getList().stream().map(this::toDTO).collect(Collectors.toList());
        return Page.build(collect, page);
    }
}

