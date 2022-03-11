package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.PlatformSourceDbDO;
import indi.uhyils.pojo.DTO.PlatformSourceDbDTO;
import indi.uhyils.pojo.entity.PlatformSourceDb;
import org.mapstruct.Mapper;

/**
* 数据库资源表(PlatformSourceDb)表 entity,DO,DTO转换工具
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Mapper(componentModel = "spring")
public abstract class PlatformSourceDbAssembler extends AbstractAssembler<PlatformSourceDbDO, PlatformSourceDb, PlatformSourceDbDTO> {

}
