package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderNodeFieldAssembler;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import indi.uhyils.pojo.DTO.OrderNodeFieldDTO;
import indi.uhyils.pojo.entity.OrderNodeField;
import indi.uhyils.repository.OrderNodeFieldRepository;
import indi.uhyils.service.OrderNodeFieldService;
import org.springframework.stereotype.Service;

/**
 * 工单节点属性样例表(OrderNodeField)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分25秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_node_field"})
public class OrderNodeFieldServiceImpl extends AbstractDoService<OrderNodeFieldDO, OrderNodeField, OrderNodeFieldDTO, OrderNodeFieldRepository, OrderNodeFieldAssembler> implements OrderNodeFieldService {

    public OrderNodeFieldServiceImpl(OrderNodeFieldAssembler assembler, OrderNodeFieldRepository repository) {
        super(assembler, repository);
    }


}
