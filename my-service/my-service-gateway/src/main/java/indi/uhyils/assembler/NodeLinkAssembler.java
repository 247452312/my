package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.NodeLinkDO;
import indi.uhyils.pojo.DTO.NodeLinkDTO;
import indi.uhyils.pojo.entity.NodeLink;
import org.mapstruct.Mapper;

/**
 * 中间节点与外部节点关联关系(NodeLink)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Mapper(componentModel = "spring")
public abstract class NodeLinkAssembler extends AbstractAssembler<NodeLinkDO, NodeLink, NodeLinkDTO> {

}
