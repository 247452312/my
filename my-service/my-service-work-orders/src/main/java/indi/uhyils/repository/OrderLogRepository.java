package indi.uhyils.repository;

import indi.uhyils.pojo.DO.OrderLogDO;
import indi.uhyils.pojo.entity.OrderLog;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * 日志表(OrderLog)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分17秒
 */
public interface OrderLogRepository extends BaseEntityRepository<OrderLogDO, OrderLog> {


}
