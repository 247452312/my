package indi.uhyils.protocol.rpc;

import indi.uhyils.pojo.DTO.TraceDetailStatisticsDTO;
import indi.uhyils.pojo.DTO.TraceInfoDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetLinkByTraceIdAndRpcIdQuery;
import indi.uhyils.pojo.DTO.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.DTO.response.LogTypeDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.BlackQuery;
import indi.uhyils.pojo.cqe.query.TraceIdQuery;
import indi.uhyils.protocol.rpc.base.DTOProvider;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年07月31日 06时43分
 */
public interface TraceInfoProvider extends DTOProvider<TraceInfoDTO> {


    /**
     * 根据traceId和rpcId获取这一串
     *
     * @param request
     *
     * @return
     */
    ServiceResult<List<TraceInfoDTO>> getLinkByTraceIdAndRpcId(GetLinkByTraceIdAndRpcIdQuery request);

    /**
     * 根据traceId获取这一串
     *
     * @param request
     *
     * @return
     */
    ServiceResult<List<TraceInfoDTO>> getLinkByTraceId(TraceIdQuery request);


    /**
     * 获取traceInfo
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Page<TraceInfoDTO>> getTraceInfoByArgAndPage(GetTraceInfoByArgAndPageRequest request);

    /**
     * 获取日志归档信息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Page<TraceDetailStatisticsDTO>> getTraceStatistics(BlackQuery request);

    /**
     * 获取日志类型
     *
     * @param request
     *
     * @return
     */
    ServiceResult<List<LogTypeDTO>> getLogType(DefaultCQE request);

}
