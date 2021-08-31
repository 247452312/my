package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.OrderNodeRouteAssembler;
import indi.uhyils.dao.OrderNodeRouteDao;
import indi.uhyils.pojo.DO.OrderNodeRouteDO;
import indi.uhyils.pojo.DTO.OrderNodeRouteDTO;
import indi.uhyils.pojo.entity.OrderNodeRoute;
import indi.uhyils.repository.OrderNodeRouteRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 节点间关联路由样例表(OrderNodeRoute)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分34秒
 */
@Repository
public class OrderNodeRouteRepositoryImpl extends AbstractRepository<OrderNodeRoute, OrderNodeRouteDO, OrderNodeRouteDao, OrderNodeRouteDTO, OrderNodeRouteAssembler> implements OrderNodeRouteRepository {

    protected OrderNodeRouteRepositoryImpl(OrderNodeRouteAssembler convert, OrderNodeRouteDao dao) {
        super(convert, dao);
    }


}
