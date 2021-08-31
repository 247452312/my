package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderNodeFieldValueAssembler;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import indi.uhyils.pojo.DTO.OrderNodeFieldValueDTO;
import indi.uhyils.pojo.entity.OrderNodeFieldValue;
import indi.uhyils.repository.OrderNodeFieldValueRepository;
import indi.uhyils.service.OrderNodeFieldValueService;
import org.springframework.stereotype.Service;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分28秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_node_field_value"})
public class OrderNodeFieldValueServiceImpl extends AbstractDoService<OrderNodeFieldValueDO, OrderNodeFieldValue, OrderNodeFieldValueDTO, OrderNodeFieldValueRepository, OrderNodeFieldValueAssembler> implements OrderNodeFieldValueService {

    public OrderNodeFieldValueServiceImpl(OrderNodeFieldValueAssembler assembler, OrderNodeFieldValueRepository repository) {
        super(assembler, repository);
    }


}
