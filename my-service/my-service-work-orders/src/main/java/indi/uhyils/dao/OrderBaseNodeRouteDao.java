package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderBaseNodeRouteEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderBaseNodeRouteDao extends DefaultDao<OrderBaseNodeRouteEntity> {


    /**
     * 根据节点id获取节点路由id
     *
     * @param nodeId 节点id
     * @return
     */
    List<OrderBaseNodeRouteEntity> getByOrderNodeId(Long nodeId);

    /**
     * 根据节点id批量删除路由
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     * @return
     */
    Integer deleteByNodeIds(@Param("ids") List<Long> ids, Long updateUser, Integer updateDate);
}
