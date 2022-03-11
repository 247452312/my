package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.PlatformSourceInterfaceDO;
import indi.uhyils.pojo.DTO.PlatformSourceInterfaceDTO;
import indi.uhyils.pojo.entity.PlatformSourceInterface;
import org.mapstruct.Mapper;

/**
* 接口资源表(PlatformSourceInterface)表 entity,DO,DTO转换工具
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Mapper(componentModel = "spring")
public abstract class PlatformSourceInterfaceAssembler extends AbstractAssembler<PlatformSourceInterfaceDO, PlatformSourceInterface, PlatformSourceInterfaceDTO> {

}
