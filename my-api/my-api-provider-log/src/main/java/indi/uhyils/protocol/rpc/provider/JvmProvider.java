package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.JvmDataStatisticsResponse;
import indi.uhyils.pojo.DTO.response.JvmInfoLogResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.base.BaseProvider;

/**
 * JVM查询接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时25分
 */
public interface JvmProvider extends BaseProvider {

    /**
     * 获取JVM数据统计信息
     *
     * @param request 默认请求
     *
     * @return JVM数据统计信息
     */
    ServiceResult<JvmDataStatisticsResponse> getJvmDataStatisticsResponse(DefaultCQE request);


    /**
     * 获取JVM历史信息 -> 存活的
     *
     * @param request 请求
     *
     * @return JVM历史信息 -> 存活的
     */
    ServiceResult<JvmInfoLogResponse> getJvmInfoLogResponse(DefaultCQE request);

}