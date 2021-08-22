package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderBaseNodeFieldDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderBaseNodeFieldDao extends DefaultDao<OrderBaseNodeFieldDO> {


    /**
     * 根据节点id获取节点字段
     *
     * @param nodeId 节点id
     * @return
     */
    List<OrderBaseNodeFieldDO> getByOrderNodeId(Long nodeId);

    /**
     * 根据节点id批量删除指定的节点属性
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     * @return
     */
    Integer deleteByNodeIds(@Param("ids") List<Long> ids, @Param("updateUser") Long updateUser, @Param("updateDate") Integer updateDate);

    /**
     * 根据工单节点的id们获取所有的工单属性
     *
     * @param orderNodeIds
     * @return
     */
    List<OrderBaseNodeFieldDO> getByOrderNodeIds(@Param("orderNodeIds") List<Long> orderNodeIds);

}
