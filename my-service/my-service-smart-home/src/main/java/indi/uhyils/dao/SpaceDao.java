package indi.uhyils.dao;

import indi.uhyils.pojo.DO.SpaceDO;
import org.apache.ibatis.annotations.Mapper;
import indi.uhyils.dao.base.DefaultDao;

/**
 * 空间坐标表(Space)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分57秒
 */
@Mapper
public interface SpaceDao extends DefaultDao<SpaceDO> {


}
