package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.InstructionsDO;
import indi.uhyils.pojo.DTO.InstructionsDTO;
import indi.uhyils.pojo.entity.Instructions;

/**
 * 说明书表(Instructions)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分24秒
 */
@Assembler
public class InstructionsAssembler extends AbstractAssembler<InstructionsDO, Instructions, InstructionsDTO> {

    @Override
    public Instructions toEntity(InstructionsDO dO) {
        return new Instructions(dO);
    }

    @Override
    public Instructions toEntity(InstructionsDTO dto) {
        return new Instructions(toDo(dto));
    }

    @Override
    protected Class<InstructionsDO> getDoClass() {
        return InstructionsDO.class;
    }

    @Override
    protected Class<InstructionsDTO> getDtoClass() {
        return InstructionsDTO.class;
    }
}

