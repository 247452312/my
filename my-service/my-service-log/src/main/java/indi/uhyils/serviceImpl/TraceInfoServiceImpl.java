package indi.uhyils.serviceImpl;

import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.factory.TraceInfoFactory;
import indi.uhyils.pojo.model.TraceInfoEntity;
import indi.uhyils.pojo.request.GetLinkByTraceIdRequest;
import indi.uhyils.pojo.request.base.DefaultPageRequest;
import indi.uhyils.pojo.request.base.LongRequest;
import indi.uhyils.pojo.response.GetAllLinkByTraceIdResponse;
import indi.uhyils.pojo.response.GetLinkByTraceIdResponse;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.response.trace.OneTraceLink;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.TraceInfoService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年07月31日 06时43分
 */
@RpcService
public class TraceInfoServiceImpl extends BaseDefaultServiceImpl<TraceInfoEntity> implements TraceInfoService {

    @Resource
    private TraceInfoDao dao;

    @Override
    public TraceInfoDao getDao() {
        return dao;
    }

    public void setDao(TraceInfoDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<GetAllLinkByTraceIdResponse> getAllLinkByTraceId(LongRequest request) {
        List<TraceInfoEntity> traceInfoEntities = dao.getByTraceId(request.getValue());
        Map<Long, OneTraceLink> byTraceLink = TraceInfoFactory.createByTraceLink(traceInfoEntities);
        return ServiceResult.buildSuccessResult(GetAllLinkByTraceIdResponse.build(byTraceLink.get(request.getValue())), request);
    }

    @Override
    public ServiceResult<Page<Long>> getTraceIdByPage(DefaultPageRequest request) {
        List<Long> traceIds = dao.getTraceIdByPage(request);
        Integer count = dao.getTraceIdByPageCount(request);
        Page<Long> build = Page.build(request, traceIds, count);
        return ServiceResult.buildSuccessResult(build, request);
    }

    @Override
    public ServiceResult<GetLinkByTraceIdResponse> getLinkByTraceId(GetLinkByTraceIdRequest request) {
        List<TraceInfoEntity> traceIds = dao.getLinkByTraceId(request);
        traceIds = traceIds.stream()
                           .filter(t -> t.getRpcId() != null)
                           .filter(t -> t.getRpcId().lastIndexOf(".") == request.getRpcId().length())
                           .collect(Collectors.toList());
        return ServiceResult.buildSuccessResult(GetLinkByTraceIdResponse.build(traceIds), request);
    }

}
