package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.PlatformNodeLinkDO;
import indi.uhyils.pojo.DTO.PlatformNodeLinkDTO;
import indi.uhyils.pojo.entity.PlatformNodeLink;
import org.mapstruct.Mapper;

/**
* 节点关联表(PlatformNodeLink)表 entity,DO,DTO转换工具
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Mapper(componentModel = "spring")
public abstract class PlatformNodeLinkAssembler extends AbstractAssembler<PlatformNodeLinkDO, PlatformNodeLink, PlatformNodeLinkDTO> {

}
