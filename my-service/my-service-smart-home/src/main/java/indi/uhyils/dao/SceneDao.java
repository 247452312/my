package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.SceneDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Scene)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分53秒
 */
@Mapper
public interface SceneDao extends DefaultDao<SceneDO> {


}
