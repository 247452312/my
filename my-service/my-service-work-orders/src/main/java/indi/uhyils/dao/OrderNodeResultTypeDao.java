package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.OrderNodeResultTypeDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderNodeResultTypeDao extends DefaultDao<OrderNodeResultTypeDO> {


    /**
     * 通过工单节点id获取所有的结果
     *
     * @param orderNodeId
     *
     * @return
     */
    List<OrderNodeResultTypeDO> getByOrderNodeId(Long orderNodeId);

    /**
     * 根据工单节点id获取所有的结果类型
     *
     * @param orderNodeIds
     *
     * @return
     */
    List<OrderNodeResultTypeDO> getByOrderNodeIds(@Param("orderNodeIds") List<Long> orderNodeIds);
}
