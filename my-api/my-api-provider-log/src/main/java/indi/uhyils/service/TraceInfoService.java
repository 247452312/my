package indi.uhyils.service;

import indi.uhyils.pojo.model.TraceInfoEntity;
import indi.uhyils.pojo.request.GetLinkByTraceIdRequest;
import indi.uhyils.pojo.request.base.DefaultPageRequest;
import indi.uhyils.pojo.request.base.LongRequest;
import indi.uhyils.pojo.response.GetAllLinkByTraceIdResponse;
import indi.uhyils.pojo.response.GetLinkByTraceIdResponse;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年07月31日 06时43分
 */
public interface TraceInfoService extends DefaultEntityService<TraceInfoEntity> {

    /**
     * 获取trace连接
     *
     * @param request
     *
     * @return
     */
    ServiceResult<GetAllLinkByTraceIdResponse> getAllLinkByTraceId(LongRequest request);

    /**
     * 分页获取page
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Page<Long>> getTraceIdByPage(DefaultPageRequest request);


    /**
     * 根据traceId获取这一串
     *
     * @param request
     *
     * @return
     */
    ServiceResult<GetLinkByTraceIdResponse> getLinkByTraceId(GetLinkByTraceIdRequest request);

}
