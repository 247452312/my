package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.ServerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 13时34分
 */
@Mapper
public interface ServerDao extends DefaultDao<ServerEntity> {

    /**
     * 获取所有的服务器信息
     *
     * @return 所有服务器信息(只有id和name)
     */
    ArrayList<ServerEntity> getServersIdAndName();

    /**
     * 根据服务器id获取名称
     *
     * @param id id
     * @return 名称
     */
    String getNameById(String id);
}
