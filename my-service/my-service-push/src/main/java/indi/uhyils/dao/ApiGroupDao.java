package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.ApiGroupEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface ApiGroupDao extends DefaultDao<ApiGroupEntity> {

    /**
     * 获取全部
     *
     * @return
     */
    List<ApiGroupEntity> getAll();

    /**
     * 获取所有可以被订阅的api群
     *
     * @return 可以被订阅的api群
     */
    ArrayList<ApiGroupEntity> getCanBeSubscribed();

}
