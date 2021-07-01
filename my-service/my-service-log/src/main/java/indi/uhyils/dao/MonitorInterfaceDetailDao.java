package indi.uhyils.dao;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.LogMonitorInterfaceCallEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 14时55分
 */
@Mapper
public interface MonitorInterfaceDetailDao extends DefaultDao<LogMonitorInterfaceCallEntity> {

    /**
     * 获取从从开始时间到现在的服务调用次数
     *
     * @param time 开始时间
     * @return 从开始时间到现在的服务调用次数
     */
    Integer getCountByStartTime(Long time);
}
