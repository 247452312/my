package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.NodeDO;
import indi.uhyils.pojo.DTO.NodeDTO;
import indi.uhyils.pojo.entity.Node;
import org.mapstruct.Mapper;

/**
 * 转换节点表(Node)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Mapper(componentModel = "spring")
public abstract class NodeAssembler extends AbstractAssembler<NodeDO, Node, NodeDTO> {

}
