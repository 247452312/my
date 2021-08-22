package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderNodeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderNodeDao extends DefaultDao<OrderNodeDO> {


    /**
     * 根据多个id批量获取
     *
     * @param nodeIds
     * @return
     */
    List<OrderNodeDO> getByIds(@Param("nodeIds") Set<Long> nodeIds);

    /**
     * 将工单节点置为失败
     *
     * @param orderNodeId     工单节点id
     * @param orderStatus     工单状态
     * @param orderResultType 工单结果类型
     * @param msg             失败信息
     */
    void makeOrderFault(@Param("orderNodeId") Long orderNodeId, @Param("orderStatus") Integer orderStatus, @Param("orderResultType") Integer orderResultType, @Param("msg") String msg);

    /**
     * 根据上一个节点和结果获取下一节点
     *
     * @param nodeId
     * @param resultId
     * @return
     */
    OrderNodeDO getNextNodeByNodeAndResult(@Param("nodeId") Long nodeId, @Param("resultId") Long resultId);

    /**
     * 根据id批量删除
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     * @return
     */
    Integer deleteByIds(List<Long> ids, Long updateUser, Integer updateDate);

    /**
     * 根据工单id获取全部的工单节点
     *
     * @param id
     * @return
     */
    List<OrderNodeDO> getByOrderId(Long id);
}
