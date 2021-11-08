package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.InstructionsDO;
import indi.uhyils.pojo.DTO.InstructionsDTO;
import indi.uhyils.pojo.entity.Instructions;
import org.mapstruct.Mapper;

/**
 * 说明书表(Instructions)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分24秒
 */
@Mapper(componentModel = "spring")
public abstract class InstructionsAssembler extends AbstractAssembler<InstructionsDO, Instructions, InstructionsDTO> {

}
