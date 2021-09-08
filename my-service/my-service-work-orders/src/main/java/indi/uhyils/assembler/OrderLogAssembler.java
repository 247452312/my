package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.OrderLogDO;
import indi.uhyils.pojo.DTO.OrderLogDTO;
import indi.uhyils.pojo.entity.OrderLog;

/**
 * 日志表(OrderLog)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分17秒
 */
@Assembler
public class OrderLogAssembler extends AbstractAssembler<OrderLogDO, OrderLog, OrderLogDTO> {

    @Override
    public OrderLog toEntity(OrderLogDO dO) {
        return new OrderLog(dO);
    }

    @Override
    public OrderLog toEntity(OrderLogDTO dto) {
        return new OrderLog(toDo(dto));
    }

    @Override
    protected Class<OrderLogDO> getDoClass() {
        return OrderLogDO.class;
    }

    @Override
    protected Class<OrderLogDTO> getDtoClass() {
        return OrderLogDTO.class;
    }
}
