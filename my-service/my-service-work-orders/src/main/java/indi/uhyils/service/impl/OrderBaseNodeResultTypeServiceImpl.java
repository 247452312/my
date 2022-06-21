package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderBaseNodeResultTypeAssembler;
import indi.uhyils.pojo.DO.OrderBaseNodeResultTypeDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeResultTypeDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeResultType;
import indi.uhyils.repository.OrderBaseNodeResultTypeRepository;
import indi.uhyils.service.OrderBaseNodeResultTypeService;
import org.springframework.stereotype.Service;

/**
 * 工单节点处理结果样例表(OrderBaseNodeResultType)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分05秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_base_node_result_type"})
public class OrderBaseNodeResultTypeServiceImpl extends AbstractDoService<OrderBaseNodeResultTypeDO, OrderBaseNodeResultType, OrderBaseNodeResultTypeDTO, OrderBaseNodeResultTypeRepository, OrderBaseNodeResultTypeAssembler> implements OrderBaseNodeResultTypeService {

    public OrderBaseNodeResultTypeServiceImpl(OrderBaseNodeResultTypeAssembler assembler, OrderBaseNodeResultTypeRepository repository) {
        super(assembler, repository);
    }


}
