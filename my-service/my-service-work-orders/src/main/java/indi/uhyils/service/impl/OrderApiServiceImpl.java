package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.OrderApiAssembler;
import indi.uhyils.pojo.DO.OrderApiDO;
import indi.uhyils.pojo.DTO.OrderApiDTO;
import indi.uhyils.pojo.entity.OrderApi;
import indi.uhyils.repository.OrderApiRepository;
import indi.uhyils.service.OrderApiService;
import org.springframework.stereotype.Service;

/**
 * 节点api表(OrderApi)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分46秒
 */
@Service
@ReadWriteMark(tables = {"sys_order_api"})
public class OrderApiServiceImpl extends AbstractDoService<OrderApiDO, OrderApi, OrderApiDTO, OrderApiRepository, OrderApiAssembler> implements OrderApiService {

    public OrderApiServiceImpl(OrderApiAssembler assembler, OrderApiRepository repository) {
        super(assembler, repository);
    }


}
