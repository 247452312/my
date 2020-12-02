package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderBaseNodeResultTypeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderBaseNodeResultTypeDao extends DefaultDao<OrderBaseNodeResultTypeEntity> {


    /**
     * 根据节点id获取节点结果类型
     *
     * @param nodeId 节点id
     * @return
     */
    List<OrderBaseNodeResultTypeEntity> getByOrderNodeId(String nodeId);

    /**
     * 根据节点id删除所有结果类型
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     * @return
     */
    Integer deleteByNodeIds(@Param("ids") List<String> ids, String updateUser, Integer updateDate);
}
