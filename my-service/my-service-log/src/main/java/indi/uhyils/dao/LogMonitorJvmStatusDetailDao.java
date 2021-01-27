package indi.uhyils.dao;

import indi.uhyils.dao.base.BaseDao;
import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.LogMonitorJvmStatusEntity;
import indi.uhyils.pojo.request.model.Arg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时55分
 */
@Mapper
public interface LogMonitorJvmStatusDetailDao extends DefaultDao<LogMonitorJvmStatusEntity> {
}
