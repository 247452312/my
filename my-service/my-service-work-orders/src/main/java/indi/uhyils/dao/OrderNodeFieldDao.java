package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderNodeFieldEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderNodeFieldDao extends DefaultDao<OrderNodeFieldEntity> {


    /**
     * 批量获取工单节点属性
     *
     * @param fieldIds
     * @return
     */
    List<OrderNodeFieldEntity> getByIds(Set<Long> fieldIds);

    /**
     * 根据工单节点id获取此节点的属性们
     *
     * @param orderNodeId
     * @return
     */
    List<OrderNodeFieldEntity> getByOrderNodeId(Long orderNodeId);

    /**
     * 根据节点id批量删除指定的节点属性
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     * @return
     */
    Integer deleteByNodeIds(List<Long> ids, Long updateUser, Integer updateDate);

    /**
     * 根据工单节点们获取全部的工单节点属性
     *
     * @param orderNodeIds
     * @return
     */
    List<OrderNodeFieldEntity> getByOrderNodeIds(@Param("orderNodeIds") List<Long> orderNodeIds);
}
