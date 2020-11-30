package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderNodeFieldEntity;
import org.apache.ibatis.annotations.Mapper;

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
    List<OrderNodeFieldEntity> getByIds(Set<String> fieldIds);

    /**
     * 根据工单节点id获取此节点的属性们
     *
     * @param orderNodeId
     * @return
     */
    List<OrderNodeFieldEntity> getByOrderNodeId(String orderNodeId);
}