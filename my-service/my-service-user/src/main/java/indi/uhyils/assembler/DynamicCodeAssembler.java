package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.DynamicCodeDO;
import indi.uhyils.pojo.DTO.DynamicCodeDTO;
import indi.uhyils.pojo.entity.DynamicCode;
import org.mapstruct.Mapper;

/**
* 动态代码主表(DynamicCode)表 entity,DO,DTO转换工具
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年02月11日 18时53分
*/
@Mapper(componentModel = "spring")
public abstract class DynamicCodeAssembler extends AbstractAssembler<DynamicCodeDO, DynamicCode, DynamicCodeDTO> {

}
