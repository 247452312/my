package indi.uhyils.repository;

import indi.uhyils.pojo.DO.LogMonitorDO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.JvmDataStatisticsResponse;
import indi.uhyils.pojo.DTO.response.JvmInfoLogResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.entity.LogMonitor;
import indi.uhyils.repository.base.BaseEntityRepository;

/**
 * JVM日志表(LogMonitor)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
public interface LogMonitorRepository extends BaseEntityRepository<LogMonitorDO, LogMonitor> {


    /**
     * 获取JVM数据统计信息
     *
     * @param request 默认请求
     *
     * @return JVM数据统计信息
     */
    JvmDataStatisticsResponse getJvmDataStatisticsResponse(DefaultCQE request);


    /**
     * 获取JVM历史信息 -> 存活的
     *
     * @param request 请求
     *
     * @return JVM历史信息 -> 存活的
     */
    JvmInfoLogResponse getJvmInfoLogResponse(DefaultCQE request);

}
