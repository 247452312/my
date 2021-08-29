package indi.uhyils.service;


import indi.uhyils.pojo.DTO.TraceDetailStatisticsDTO;
import indi.uhyils.pojo.DTO.TraceInfoDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetLinkByTraceIdAndRpcIdQuery;
import indi.uhyils.pojo.DTO.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.DTO.response.GetLogTypeResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.Query;
import indi.uhyils.pojo.cqe.query.TraceIdQuery;
import java.util.List;

/**
 * (TraceInfo)表 内部服务接口
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
public interface TraceInfoService extends BaseDoService<TraceInfoDTO> {

    /**
     * 根据traceId和rpcId获取这一串
     *
     * @param request
     *
     * @return
     */
    List<TraceInfoDTO> getLinkByTraceIdAndRpcId(GetLinkByTraceIdAndRpcIdQuery request);

    /**
     * 根据traceId获取这一串
     *
     * @param request
     *
     * @return
     */
    List<TraceInfoDTO> getLinkByTraceId(TraceIdQuery request);


    /**
     * 获取traceInfo
     *
     * @param request
     *
     * @return
     */
    Page<TraceInfoDTO> getTraceInfoByArgAndPage(GetTraceInfoByArgAndPageRequest request);

    /**
     * 获取日志归档信息
     *
     * @param request
     *
     * @return
     */
    Page<TraceDetailStatisticsDTO> getTraceStatistics(Query request);

    /**
     * 获取日志类型
     *
     * @param request
     *
     * @return
     */
    List<GetLogTypeResponse> getLogType(DefaultCQE request);

}
