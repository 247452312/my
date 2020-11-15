package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderNodeFieldValueEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单节点属性真实值表(OrderNodeFieldValue)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月15日 16时16分06秒
 */
@Mapper
public interface OrderNodeFieldValueDao extends DefaultDao<OrderNodeFieldValueEntity> {


}
