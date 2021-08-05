package indi.uhyils.service;

import indi.uhyils.pojo.model.TraceDetailStatisticsView;
import indi.uhyils.pojo.model.TraceInfoEntity;
import indi.uhyils.pojo.request.GetLinkByTraceIdRequest;
import indi.uhyils.pojo.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.request.base.DefaultPageRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.GetLinkByTraceIdResponse;
import indi.uhyils.pojo.response.GetLogTypeResponse;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;
import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年07月31日 06时43分
 */
public interface TraceInfoService extends DefaultEntityService<TraceInfoEntity> {


    /**
     * 根据traceId获取这一串
     *
     * @param request
     *
     * @return
     */
    ServiceResult<GetLinkByTraceIdResponse> getLinkByTraceId(GetLinkByTraceIdRequest request);


    /**
     * 获取traceInfo
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Page<TraceInfoEntity>> getTraceInfoByArgAndPage(GetTraceInfoByArgAndPageRequest request);

    /**
     * 获取日志归档信息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Page<TraceDetailStatisticsView>> getTraceStatistics(DefaultPageRequest request);

    /**
     * 获取日志类型
     *
     * @param request
     *
     * @return
     */
    ServiceResult<ArrayList<GetLogTypeResponse>> getLogType(DefaultRequest request);

}
