package indi.uhyils.repository;

import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import indi.uhyils.pojo.entity.OrderNodeField;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 工单节点属性样例表(OrderNodeField)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分24秒
 */
public interface OrderNodeFieldRepository extends BaseEntityRepository<OrderNodeFieldDO, OrderNodeField> {


    /**
     * 获取node的field
     *
     * @param nodeId
     *
     * @return
     */
    List<OrderNodeField> findByNodeId(Long nodeId);

    /**
     * 获取node
     *
     * @param nodeIds
     *
     * @return
     */
    List<OrderNodeField> findByNodeIds(List<Long> nodeIds);

}
