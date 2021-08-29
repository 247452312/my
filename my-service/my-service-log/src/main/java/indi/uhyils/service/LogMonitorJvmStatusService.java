package indi.uhyils.service;


import indi.uhyils.pojo.DTO.LogMonitorJvmStatusDTO;
import indi.uhyils.pojo.DTO.response.JvmDataStatisticsResponse;
import indi.uhyils.pojo.DTO.response.JvmInfoLogResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;

/**
 * JVM状态子表(LogMonitorJvmStatus)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分53秒
 */
public interface LogMonitorJvmStatusService extends BaseDoService<LogMonitorJvmStatusDTO> {

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
