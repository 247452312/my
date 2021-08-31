package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.OrderNodeFieldValueDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分26秒
 */
@Mapper
public interface OrderNodeFieldValueDao extends DefaultDao<OrderNodeFieldValueDO> {


}
