package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.OrderNodeFieldDO;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderNodeFieldDao extends DefaultDao<OrderNodeFieldDO> {


    /**
     * 批量获取工单节点属性
     *
     * @param fieldIds
     *
     * @return
     */
    List<OrderNodeFieldDO> getByIds(Set<Long> fieldIds);

    /**
     * 根据工单节点id获取此节点的属性们
     *
     * @param orderNodeId
     *
     * @return
     */
    List<OrderNodeFieldDO> getByOrderNodeId(Long orderNodeId);

    /**
     * 根据节点id批量删除指定的节点属性
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     *
     * @return
     */
    Integer deleteByNodeIds(List<Long> ids, Long updateUser, Integer updateDate);

    /**
     * 根据工单节点们获取全部的工单节点属性
     *
     * @param orderNodeIds
     *
     * @return
     */
    List<OrderNodeFieldDO> getByOrderNodeIds(@Param("orderNodeIds") List<Long> orderNodeIds);
}
