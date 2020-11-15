package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.OrderNodeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 10时11分
 */
@Mapper
public interface OrderNodeDao extends DefaultDao<OrderNodeEntity> {


    /**
     * 根据多个id批量获取
     *
     * @param nodeIds
     * @return
     */
    List<OrderNodeEntity> getByIds(Set<String> nodeIds);
}
