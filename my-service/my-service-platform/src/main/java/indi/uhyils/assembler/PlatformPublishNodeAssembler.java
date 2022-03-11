package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.PlatformPublishNodeDO;
import indi.uhyils.pojo.DTO.PlatformPublishNodeDTO;
import indi.uhyils.pojo.entity.PlatformPublishNode;
import org.mapstruct.Mapper;

/**
* 发布节点表(PlatformPublishNode)表 entity,DO,DTO转换工具
*
* @author uhyils <247452312@qq.com>
* @version 1.0
* @date 文件创建日期 2022年03月11日 09时31分
*/
@Mapper(componentModel = "spring")
public abstract class PlatformPublishNodeAssembler extends AbstractAssembler<PlatformPublishNodeDO, PlatformPublishNode, PlatformPublishNodeDTO> {

}
