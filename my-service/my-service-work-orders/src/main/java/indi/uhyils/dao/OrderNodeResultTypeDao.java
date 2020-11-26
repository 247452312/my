package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderNodeResultTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderNodeResultTypeDao extends DefaultDao<OrderNodeResultTypeEntity> {


    /**
     * 通过工单节点id获取所有的结果
     *
     * @param orderNodeId
     * @return
     */
    List<OrderNodeResultTypeEntity> getByOrderNodeId(String orderNodeId);
}
