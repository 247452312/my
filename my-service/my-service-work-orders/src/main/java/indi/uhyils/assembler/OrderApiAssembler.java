package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderApiDO;
import indi.uhyils.pojo.DTO.OrderApiDTO;
import indi.uhyils.pojo.entity.OrderApi;

/**
 * 节点api表(OrderApi)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分45秒
 */
@Assembler
public class OrderApiAssembler extends AbstractAssembler<OrderApiDO, OrderApi, OrderApiDTO> {

    @Override
    public OrderApi toEntity(OrderApiDO dO) {
        return new OrderApi(dO);
    }

    @Override
    public OrderApi toEntity(OrderApiDTO dto) {
        return new OrderApi(toDo(dto));
    }

    @Override
    protected Class<OrderApiDO> getDoClass() {
        return OrderApiDO.class;
    }

    @Override
    protected Class<OrderApiDTO> getDtoClass() {
        return OrderApiDTO.class;
    }
}

