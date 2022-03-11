package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.PlatformPowerDO;
import indi.uhyils.pojo.DTO.PlatformPowerDTO;
import indi.uhyils.pojo.entity.PlatformPower;
import org.mapstruct.Mapper;

/**
* 权限表(PlatformPower)表 entity,DO,DTO转换工具
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Mapper(componentModel = "spring")
public abstract class PlatformPowerAssembler extends AbstractAssembler<PlatformPowerDO, PlatformPower, PlatformPowerDTO> {

}
