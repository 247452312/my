package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderBaseNodeFieldEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderBaseNodeFieldDao extends DefaultDao<OrderBaseNodeFieldEntity> {


    /**
     * 根据节点id获取节点字段
     *
     * @param nodeId 节点id
     * @return
     */
    List<OrderBaseNodeFieldEntity> getByOrderNodeId(String nodeId);

    /**
     * 根据节点id批量删除指定的节点属性
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     * @return
     */
    Integer deleteByNodeIds(@Param("ids") List<String> ids, @Param("updateUser") String updateUser, @Param("updateDate") Integer updateDate);
}
