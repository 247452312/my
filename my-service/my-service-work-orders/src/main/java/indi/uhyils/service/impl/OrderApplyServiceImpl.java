package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderApplyAssembler;
import indi.uhyils.pojo.DO.OrderApplyDO;
import indi.uhyils.pojo.DTO.OrderApplyDTO;
import indi.uhyils.pojo.entity.OrderApply;
import indi.uhyils.repository.OrderApplyRepository;
import indi.uhyils.service.OrderApplyService;
import org.springframework.stereotype.Service;

/**
 * (OrderApply)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分50秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_apply"})
public class OrderApplyServiceImpl extends AbstractDoService<OrderApplyDO, OrderApply, OrderApplyDTO, OrderApplyRepository, OrderApplyAssembler> implements OrderApplyService {

    public OrderApplyServiceImpl(OrderApplyAssembler assembler, OrderApplyRepository repository) {
        super(assembler, repository);
    }


}
