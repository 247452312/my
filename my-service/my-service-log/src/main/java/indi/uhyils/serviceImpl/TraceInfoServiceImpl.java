package indi.uhyils.serviceImpl;

import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.pojo.model.TraceDetailStatisticsView;
import indi.uhyils.pojo.model.TraceInfoEntity;
import indi.uhyils.pojo.request.GetLinkByTraceIdRequest;
import indi.uhyils.pojo.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.request.base.DefaultPageRequest;
import indi.uhyils.pojo.response.GetLinkByTraceIdResponse;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.TraceInfoService;
import java.util.ArrayList;
import java.util.List;
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
    public ServiceResult<GetLinkByTraceIdResponse> getLinkByTraceId(GetLinkByTraceIdRequest request) {
        List<TraceInfoEntity> traceIds = dao.getLinkByTraceIdAndRpcIdPrefix(request);
        return ServiceResult.buildSuccessResult(GetLinkByTraceIdResponse.build(traceIds));
    }

    @Override
    public ServiceResult<ArrayList<TraceInfoEntity>> getTraceInfoByArgAndPage(GetTraceInfoByArgAndPageRequest request) {
        ArrayList<TraceInfoEntity> list = dao.getTraceInfoByArgAndPage(request);

        return ServiceResult.buildSuccessResult(list);
    }

    @Override
    public ServiceResult<Page<TraceDetailStatisticsView>> getTraceStatistics(DefaultPageRequest request) {
        List<TraceDetailStatisticsView> list = dao.getTraceStatistics(request);
        Integer count = dao.getTraceStatisticsCount(request);
        return ServiceResult.buildSuccessResult(Page.build(request, list, count));
    }


}
