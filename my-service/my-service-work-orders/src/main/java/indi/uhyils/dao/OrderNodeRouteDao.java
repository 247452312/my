package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderNodeRouteEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderNodeRouteDao extends DefaultDao<OrderNodeRouteEntity> {


    /**
     * 根据来源工单节点id获取所有的路由
     *
     * @param prevOrderNodeId
     * @return
     */
    List<OrderNodeRouteEntity> getByPrevOrderNodeId(String prevOrderNodeId);

    /**
     * 根据节点id批量删除路由
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     * @return
     */
    Integer deleteByNodeIds(List<String> ids, String updateUser, Integer updateDate);
}
