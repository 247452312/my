package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.PlatformInternalNodeDO;
import indi.uhyils.pojo.DTO.PlatformInternalNodeDTO;
import indi.uhyils.pojo.entity.PlatformInternalNode;
import org.mapstruct.Mapper;

/**
* 内部节点表(PlatformInternalNode)表 entity,DO,DTO转换工具
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Mapper(componentModel = "spring")
public abstract class PlatformInternalNodeAssembler extends AbstractAssembler<PlatformInternalNodeDO, PlatformInternalNode, PlatformInternalNodeDTO> {

}
