package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderBaseNodeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderBaseNodeDao extends DefaultDao<OrderBaseNodeEntity> {


    /**
     * 根据主表获取OrderNode
     *
     * @param baseInfoId 主表id
     * @return orderNode
     */
    List<OrderBaseNodeEntity> getNoHiddenByOrderId(Long baseInfoId);

    /**
     * 根据id批量删除
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     * @return
     */
    Integer deleteByIds(List<Long> ids, Long updateUser, Integer updateDate);
}
