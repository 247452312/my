package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.OrderNodeRouteDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 节点间关联路由样例表(OrderNodeRoute)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分32秒
 */
@Mapper
public interface OrderNodeRouteDao extends DefaultDao<OrderNodeRouteDO> {


}
