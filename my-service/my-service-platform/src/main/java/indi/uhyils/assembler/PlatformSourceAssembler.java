package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.PlatformSourceDO;
import indi.uhyils.pojo.DTO.PlatformSourceDTO;
import indi.uhyils.pojo.entity.PlatformSource;
import org.mapstruct.Mapper;

/**
* 资源主表(PlatformSource)表 entity,DO,DTO转换工具
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Mapper(componentModel = "spring")
public abstract class PlatformSourceAssembler extends AbstractAssembler<PlatformSourceDO, PlatformSource, PlatformSourceDTO> {

}
