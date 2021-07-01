package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.LogMonitorJvmStatusEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时55分
 */
@Mapper
public interface MonitorJvmStatusDetailDao extends DefaultDao<LogMonitorJvmStatusEntity> {

    /**
     * 根据主表id获取分表数据
     *
     * @param id 主表id
     * @return 主表id对应的分表数据
     */
    List<LogMonitorJvmStatusEntity> getByMonitorId(Long id);
}
