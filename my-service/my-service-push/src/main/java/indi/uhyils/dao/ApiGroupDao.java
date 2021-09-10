package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.ApiGroupDO;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface ApiGroupDao extends DefaultDao<ApiGroupDO> {

    /**
     * 获取全部
     *
     * @return
     */
    List<ApiGroupDO> getAll();

    /**
     * 获取所有可以被订阅的api群
     *
     * @return 可以被订阅的api群
     */
    ArrayList<ApiGroupDO> getCanBeSubscribed();

}
