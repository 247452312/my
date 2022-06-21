package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.ContentDO;
import indi.uhyils.pojo.DTO.ContentDTO;
import indi.uhyils.pojo.entity.Content;
import org.mapstruct.Mapper;

/**
 * 公共参数(Content)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分15秒
 */
@Mapper(componentModel = "spring")
public abstract class ContentAssembler extends AbstractAssembler<ContentDO, Content, ContentDTO> {

}
