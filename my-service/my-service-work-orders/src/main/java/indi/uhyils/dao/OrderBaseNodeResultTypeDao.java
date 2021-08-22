package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderBaseNodeResultTypeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderBaseNodeResultTypeDao extends DefaultDao<OrderBaseNodeResultTypeDO> {


    /**
     * 根据节点id获取节点结果类型
     *
     * @param nodeId 节点id
     * @return
     */
    List<OrderBaseNodeResultTypeDO> getByOrderNodeId(Long nodeId);

    /**
     * 根据节点id删除所有结果类型
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     * @return
     */
    Integer deleteByNodeIds(@Param("ids") List<Long> ids, Long updateUser, Integer updateDate);

    /**
     * 根据所有的工单节点id们获取所有的结果类型
     *
     * @param orderNodeIds
     * @return
     */
    List<OrderBaseNodeResultTypeDO> getByOrderNodeIds(@Param("orderNodeIds") List<Long> orderNodeIds);
}
