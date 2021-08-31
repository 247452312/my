package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工单节点样例表(OrderBaseNode)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时58分56秒
 */
@Mapper
public interface OrderBaseNodeDao extends DefaultDao<OrderBaseNodeDO> {


}
