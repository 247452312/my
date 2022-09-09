package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.CallNodeDO;
import indi.uhyils.pojo.DTO.CallNodeDTO;
import indi.uhyils.pojo.entity.CallNode;
import org.mapstruct.Mapper;

/**
 * 调用节点表, 真正调用的节点(CallNode)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年09月09日 15时45分
 */
@Mapper(componentModel = "spring")
public abstract class CallNodeAssembler extends AbstractAssembler<CallNodeDO, CallNode, CallNodeDTO> {

}
