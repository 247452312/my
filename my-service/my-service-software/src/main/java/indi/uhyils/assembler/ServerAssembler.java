package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ServerDO;
import indi.uhyils.pojo.DTO.ServerDTO;
import indi.uhyils.pojo.entity.Server;
import org.mapstruct.Mapper;

/**
 * 服务器表(Server)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 08时42分16秒
 */
@Mapper(componentModel = "spring")
public abstract class ServerAssembler extends AbstractAssembler<ServerDO, Server, ServerDTO> {

}
