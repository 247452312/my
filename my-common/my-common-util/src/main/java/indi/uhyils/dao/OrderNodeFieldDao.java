package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工单节点属性样例表(OrderNodeField)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时59分23秒
 */
@Mapper
public interface OrderNodeFieldDao extends DefaultDao<OrderNodeFieldDO> {


}
