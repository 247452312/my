package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.OrderBaseNodeDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderBaseNodeDao extends DefaultDao<OrderBaseNodeDO> {


    /**
     * 根据主表获取OrderNode
     *
     * @param baseInfoId 主表id
     *
     * @return orderNode
     */
    List<OrderBaseNodeDO> getNoHiddenByOrderId(Long baseInfoId);

    /**
     * 根据id批量删除
     *
     * @param ids
     * @param updateUser
     * @param updateDate
     *
     * @return
     */
    Integer deleteByIds(List<Long> ids, Long updateUser, Integer updateDate);
}
