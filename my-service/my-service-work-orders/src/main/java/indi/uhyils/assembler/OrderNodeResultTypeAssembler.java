package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.DTO.OrderNodeResultTypeDTO;
import indi.uhyils.pojo.entity.OrderNodeResultType;
import org.mapstruct.Mapper;

/**
 * 工单节点处理结果样例表(OrderNodeResultType)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分30秒
 */
@Mapper(componentModel = "spring")
public abstract class OrderNodeResultTypeAssembler extends AbstractAssembler<OrderNodeResultTypeDO, OrderNodeResultType, OrderNodeResultTypeDTO> {

}
