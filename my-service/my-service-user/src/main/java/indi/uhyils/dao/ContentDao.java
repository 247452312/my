package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.ContentEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface ContentDao extends DefaultDao<ContentEntity> {

    /**
     * 根据名称返回数据库中的系统常量信息
     *
     * @param name 常量名称
     * @return 常量信息
     */
    ContentEntity getByName(String name);

}
