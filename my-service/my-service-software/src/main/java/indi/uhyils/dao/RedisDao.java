package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.RedisEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 13时23分
 */
@Mapper
public interface RedisDao extends DefaultDao<RedisEntity> {
}
