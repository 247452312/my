package indi.uhyils.repository;

import indi.uhyils.pojo.DO.OrderInfoDO;
import indi.uhyils.pojo.entity.OrderInfo;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * 工单基础信息样例表(OrderInfo)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分13秒
 */
public interface OrderInfoRepository extends BaseEntityRepository<OrderInfoDO, OrderInfo> {


    /**
     * 根据type获取订单
     *
     * @param type
     *
     * @return
     */
    List<OrderInfo> findByType(Integer type);
}
