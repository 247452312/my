package indi.uhyils.dao;

import indi.uhyils.pojo.model.ContentEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月25日 13时03分
 */
@Mapper
public interface ContentDao extends DefaultDao<ContentEntity> {

    ContentEntity getByName(String name);

}
