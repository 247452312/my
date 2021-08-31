package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderApiAssembler;
import indi.uhyils.dao.OrderApiDao;
import indi.uhyils.pojo.DO.OrderApiDO;
import indi.uhyils.pojo.DTO.OrderApiDTO;
import indi.uhyils.pojo.entity.OrderApi;
import indi.uhyils.repository.OrderApiRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 节点api表(OrderApi)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分46秒
 */
@Repository
public class OrderApiRepositoryImpl extends AbstractRepository<OrderApi, OrderApiDO, OrderApiDao, OrderApiDTO, OrderApiAssembler> implements OrderApiRepository {

    protected OrderApiRepositoryImpl(OrderApiAssembler convert, OrderApiDao dao) {
        super(convert, dao);
    }


}
