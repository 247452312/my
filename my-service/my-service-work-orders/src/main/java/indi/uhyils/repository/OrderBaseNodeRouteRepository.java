package indi.uhyils.repository;

import indi.uhyils.pojo.DO.OrderBaseNodeRouteDO;
import indi.uhyils.pojo.entity.OrderBaseNodeRoute;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 节点间关联路由样例表(OrderBaseNodeRoute)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分07秒
 */
public interface OrderBaseNodeRouteRepository extends BaseEntityRepository<OrderBaseNodeRouteDO, OrderBaseNodeRoute> {


    /**
     * 根据节点id获取节点路由
     *
     * @param nodeIds
     *
     * @return
     */
    List<OrderBaseNodeRoute> findNodeRouteByNodes(List<Identifier> nodeIds);
}
