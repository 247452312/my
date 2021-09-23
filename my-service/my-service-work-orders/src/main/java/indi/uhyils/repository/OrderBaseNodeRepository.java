package indi.uhyils.repository;

import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import indi.uhyils.pojo.entity.OrderBaseNode;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 工单节点样例表(OrderBaseNode)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分57秒
 */
public interface OrderBaseNodeRepository extends BaseEntityRepository<OrderBaseNodeDO, OrderBaseNode> {


    /**
     * 根据工单id获取节点
     *
     * @param id
     *
     * @return
     */
    List<OrderBaseNode> findNoHiddenNodeById(Identifier id);
}
