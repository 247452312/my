package indi.uhyils.serviceImpl;

import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.pojo.DO.TraceDetailStatisticsView;
import indi.uhyils.pojo.DO.TraceInfoDO;
import indi.uhyils.pojo.DTO.request.GetLinkByTraceIdAndRpcIdRequest;
import indi.uhyils.pojo.DTO.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.DTO.request.base.DefaultPageQuery;
import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.request.base.LongRequest;
import indi.uhyils.pojo.DTO.response.GetLinkByTraceIdAndRpcIdResponse;
import indi.uhyils.pojo.DTO.response.GetLogTypeResponse;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年07月31日 06时43分
 */
@RpcService
public class TraceInfoProvider extends BaseDefaultProvider<TraceInfoDO> implements indi.uhyils.protocol.rpc.provider.TraceInfoProvider {

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
    public ServiceResult<GetLinkByTraceIdAndRpcIdResponse> getLinkByTraceIdAndRpcId(GetLinkByTraceIdAndRpcIdRequest request) {
        List<TraceInfoDO> traceIds = dao.getLinkByTraceIdAndRpcIdPrefix(request);
        return ServiceResult.buildSuccessResult(GetLinkByTraceIdAndRpcIdResponse.build(traceIds));
    }

    @Override
    public ServiceResult<ArrayList<TraceInfoDO>> getLinkByTraceId(LongRequest request) {
        ArrayList<TraceInfoDO> list = dao.getTraceInfoByTraceId(request.getValue());
        return ServiceResult.buildSuccessResult(list);
    }

    @Override
    public ServiceResult<Page<TraceInfoDO>> getTraceInfoByArgAndPage(GetTraceInfoByArgAndPageRequest request) {
        List<TraceInfoDO> list = dao.getTraceInfoByArgAndPage(request);
        Integer count = dao.getTraceInfoByArgAndPageCount(request);
        Page<TraceInfoDO> build = Page.build(request, list, count);
        return ServiceResult.buildSuccessResult(build);
    }

    @Override
    public ServiceResult<Page<TraceDetailStatisticsView>> getTraceStatistics(DefaultPageQuery request) {
        List<TraceDetailStatisticsView> list = dao.getTraceStatistics(request);
        Integer count = dao.getTraceStatisticsCount(request);
        return ServiceResult.buildSuccessResult(Page.build(request, list, count));
    }

    @Override
    public ServiceResult<ArrayList<GetLogTypeResponse>> getLogType(DefaultRequest request) {
        ArrayList<GetLogTypeResponse> list = new ArrayList<>();
        for (LogTypeEnum value : LogTypeEnum.values()) {
            GetLogTypeResponse build = GetLogTypeResponse.build(value.name(), value.getCode());
            list.add(build);
        }
        return ServiceResult.buildSuccessResult(list);
    }


}
