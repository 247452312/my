package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.NodeParseDO;
import indi.uhyils.pojo.DTO.NodeParseDTO;
import indi.uhyils.pojo.entity.NodeParse;
import org.mapstruct.Mapper;

/**
 * 转换节点解析表(NodeParse)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年08月12日 08时33分
 */
@Mapper(componentModel = "spring")
public abstract class NodeParseAssembler extends AbstractAssembler<NodeParseDO, NodeParse, NodeParseDTO> {

}
