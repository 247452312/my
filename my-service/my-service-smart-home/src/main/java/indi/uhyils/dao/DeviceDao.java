package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.DeviceEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Device)表 数据库访问层
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月29日 10时47分40秒
 */
@Mapper
public interface DeviceDao extends DefaultDao<DeviceEntity> {


}
