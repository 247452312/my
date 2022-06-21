package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.OrderApiDO;
import indi.uhyils.pojo.DTO.OrderApiDTO;
import indi.uhyils.pojo.entity.OrderApi;
import org.mapstruct.Mapper;

/**
 * 节点api表(OrderApi)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分45秒
 */
@Mapper(componentModel = "spring")
public abstract class OrderApiAssembler extends AbstractAssembler<OrderApiDO, OrderApi, OrderApiDTO> {

}
