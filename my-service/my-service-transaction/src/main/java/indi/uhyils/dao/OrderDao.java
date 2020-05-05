package indi.uhyils.dao;

import indi.uhyils.model.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 13时13分
 */
@Mapper
public interface OrderDao extends DefaultDao<OrderEntity> {
}
