package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.DictDO;
import indi.uhyils.pojo.DTO.DictDTO;
import indi.uhyils.pojo.entity.Dict;

/**
 * 数据字典(Dict)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分35秒
 */
@Assembler
public class DictAssembler extends AbstractAssembler<DictDO, Dict, DictDTO> {

    @Override
    public Dict toEntity(DictDO dO) {
        return new Dict(dO);
    }

    @Override
    public Dict toEntity(DictDTO dto) {
        return new Dict(toDo(dto));
    }

    @Override
    protected Class<DictDO> getDoClass() {
        return DictDO.class;
    }

    @Override
    protected Class<DictDTO> getDtoClass() {
        return DictDTO.class;
    }
}

