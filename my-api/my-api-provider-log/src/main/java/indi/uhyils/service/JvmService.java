package indi.uhyils.service;

import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.response.JvmDataStatisticsResponse;
import indi.uhyils.pojo.response.JvmInfoLogResponse;
import indi.uhyils.pojo.response.ServiceResult;

/**
 * JVM查询接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface JvmService {

    /**
     * 获取JVM数据统计信息
     *
     * @param request 默认请求
     * @return JVM数据统计信息
     */
    ServiceResult<JvmDataStatisticsResponse> getJvmDataStatisticsResponse(DefaultRequest request);


    /**
     * 获取JVM历史信息 -> 存活的
     *
     * @param request 请求
     * @return JVM历史信息 -> 存活的
     */
    ServiceResult<JvmInfoLogResponse> getJvmInfoLogResponse(DefaultRequest request);

}
