package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderBaseNodeRouteAssembler;
import indi.uhyils.dao.OrderBaseNodeRouteDao;
import indi.uhyils.pojo.DO.OrderBaseNodeRouteDO;
import indi.uhyils.pojo.DTO.OrderBaseNodeRouteDTO;
import indi.uhyils.pojo.entity.OrderBaseNodeRoute;
import indi.uhyils.repository.OrderBaseNodeRouteRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 节点间关联路由样例表(OrderBaseNodeRoute)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分08秒
 */
@Repository
public class OrderBaseNodeRouteRepositoryImpl extends AbstractRepository<OrderBaseNodeRoute, OrderBaseNodeRouteDO, OrderBaseNodeRouteDao, OrderBaseNodeRouteDTO, OrderBaseNodeRouteAssembler> implements OrderBaseNodeRouteRepository {

    protected OrderBaseNodeRouteRepositoryImpl(OrderBaseNodeRouteAssembler convert, OrderBaseNodeRouteDao dao) {
        super(convert, dao);
    }


}
