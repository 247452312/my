package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.OrderBaseNodeResultTypeDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeResultTypeDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeResultType;
import org.mapstruct.Mapper;

/**
 * 工单节点处理结果样例表(OrderBaseNodeResultType)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分04秒
 */
@Mapper(componentModel = "spring")
public abstract class OrderBaseNodeResultTypeAssembler extends AbstractAssembler<OrderBaseNodeResultTypeDO, OrderBaseNodeResultType, OrderBaseNodeResultTypeDTO> {

}
