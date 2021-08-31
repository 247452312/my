package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderLogAssembler;
import indi.uhyils.pojo.DO.OrderLogDO;
import indi.uhyils.pojo.DTO.OrderLogDTO;
import indi.uhyils.pojo.entity.OrderLog;
import indi.uhyils.repository.OrderLogRepository;
import indi.uhyils.service.OrderLogService;
import org.springframework.stereotype.Service;

/**
 * 日志表(OrderLog)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分18秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_log"})
public class OrderLogServiceImpl extends AbstractDoService<OrderLogDO, OrderLog, OrderLogDTO, OrderLogRepository, OrderLogAssembler> implements OrderLogService {

    public OrderLogServiceImpl(OrderLogAssembler assembler, OrderLogRepository repository) {
        super(assembler, repository);
    }


}
