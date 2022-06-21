package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderNodeResultTypeAssembler;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.DTO.OrderNodeResultTypeDTO;
import indi.uhyils.pojo.entity.OrderNodeResultType;
import indi.uhyils.repository.OrderNodeResultTypeRepository;
import indi.uhyils.service.OrderNodeResultTypeService;
import org.springframework.stereotype.Service;

/**
 * 工单节点处理结果样例表(OrderNodeResultType)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分31秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_node_result_type"})
public class OrderNodeResultTypeServiceImpl extends AbstractDoService<OrderNodeResultTypeDO, OrderNodeResultType, OrderNodeResultTypeDTO, OrderNodeResultTypeRepository, OrderNodeResultTypeAssembler> implements OrderNodeResultTypeService {

    public OrderNodeResultTypeServiceImpl(OrderNodeResultTypeAssembler assembler, OrderNodeResultTypeRepository repository) {
        super(assembler, repository);
    }


}
