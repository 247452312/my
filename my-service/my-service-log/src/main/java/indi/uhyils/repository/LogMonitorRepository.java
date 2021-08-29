package indi.uhyils.repository;

import indi.uhyils.pojo.DO.LogMonitorDO;
import indi.uhyils.pojo.entity.LogMonitor;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * JVM日志表(LogMonitor)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
public interface LogMonitorRepository extends BaseEntityRepository<LogMonitorDO, LogMonitor> {

    /**
     * 获取在线服务信息
     *
     * @return
     */
    List<LogMonitor> analysisOnlineService();

}
