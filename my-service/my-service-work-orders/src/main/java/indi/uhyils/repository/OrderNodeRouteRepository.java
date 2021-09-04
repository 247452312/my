package indi.uhyils.repository;

import indi.uhyils.pojo.DO.OrderNodeRouteDO;
import indi.uhyils.pojo.entity.OrderNodeRoute;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 节点间关联路由样例表(OrderNodeRoute)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分33秒
 */
public interface OrderNodeRouteRepository extends BaseEntityRepository<OrderNodeRouteDO, OrderNodeRoute> {


    /**
     * 根据node获取路由
     *
     * @param nodeId
     *
     * @return
     */
    List<OrderNodeRoute> findByNodeId(Long nodeId);

    /**
     * 根据节点id获取路由信息
     *
     * @param collect
     *
     * @return
     */
    List<OrderNodeRoute> findByNodeIds(List<Long> collect);
}
