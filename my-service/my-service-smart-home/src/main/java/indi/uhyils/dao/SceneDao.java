package indi.uhyils.dao;

import indi.uhyils.pojo.DO.SceneDO;
import org.apache.ibatis.annotations.Mapper;
import indi.uhyils.dao.base.DefaultDao;

/**
 * 场景表(Scene)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分28秒
 */
@Mapper
public interface SceneDao extends DefaultDao<SceneDO> {


}
