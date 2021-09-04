package indi.uhyils.repository;

import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import indi.uhyils.pojo.entity.OrderNodeResultType;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 工单节点处理结果样例表(OrderNodeResultType)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分30秒
 */
public interface OrderNodeResultTypeRepository extends BaseEntityRepository<OrderNodeResultTypeDO, OrderNodeResultType> {


    /**
     * 结果
     *
     * @param nodeId
     *
     * @return
     */
    List<OrderNodeResultType> findByNodeId(Long nodeId);

    /**
     * 根据节点id们获取结果
     *
     * @param nodeIds
     *
     * @return
     */
    List<OrderNodeResultType> findByNodeIds(List<Long> nodeIds);
}
