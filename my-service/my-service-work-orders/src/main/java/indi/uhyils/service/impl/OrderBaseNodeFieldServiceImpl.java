package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderBaseNodeFieldAssembler;
import indi.uhyils.pojo.DO.OrderBaseNodeFieldDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeFieldDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeField;
import indi.uhyils.repository.OrderBaseNodeFieldRepository;
import indi.uhyils.service.OrderBaseNodeFieldService;
import org.springframework.stereotype.Service;

/**
 * 工单节点属性样例表(OrderBaseNodeField)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分01秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_base_node_field"})
public class OrderBaseNodeFieldServiceImpl extends AbstractDoService<OrderBaseNodeFieldDO, OrderBaseNodeField, OrderBaseNodeFieldDTO, OrderBaseNodeFieldRepository, OrderBaseNodeFieldAssembler> implements OrderBaseNodeFieldService {

    public OrderBaseNodeFieldServiceImpl(OrderBaseNodeFieldAssembler assembler, OrderBaseNodeFieldRepository repository) {
        super(assembler, repository);
    }


}
