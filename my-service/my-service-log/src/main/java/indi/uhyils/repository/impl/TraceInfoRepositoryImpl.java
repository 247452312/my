package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.TraceInfoAssembler;
import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.pojo.DO.TraceDetailStatisticsView;
import indi.uhyils.pojo.DO.TraceInfoDO;
import indi.uhyils.pojo.DTO.TraceInfoDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.cqe.Arg;
import indi.uhyils.pojo.cqe.query.Query;
import indi.uhyils.pojo.entity.OnlineMonitors;
import indi.uhyils.pojo.entity.Trace;
import indi.uhyils.pojo.entity.TraceInfo;
import indi.uhyils.repository.TraceInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;


/**
 * (TraceInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
@Repository
public class TraceInfoRepositoryImpl extends AbstractRepository<TraceInfo, TraceInfoDO, TraceInfoDao, TraceInfoDTO, TraceInfoAssembler> implements TraceInfoRepository {

    protected TraceInfoRepositoryImpl(TraceInfoAssembler convert, TraceInfoDao dao) {
        super(convert, dao);
    }


    @Override
    public List<TraceInfo> findTraceInfoByTraceIdAndRpcId(Trace trace) {
        List<TraceInfoDO> traceInfos = dao.getLinkByTraceIdAndRpcIdPrefix(trace.traceId(), trace.rpcId());
        return assembler.listToEntity(traceInfos);
    }

    @Override
    public Integer findWebRequestCount(OnlineMonitors logMonitors) {
        return dao.getCountByTypeAndStartTime(LogTypeEnum.CONTROLLER.getCode(), logMonitors.earlyStartTime());
    }

    @Override
    public Integer findRpcExecuteCount(OnlineMonitors logMonitors) {
        /* 获取接口调用次数 */
        return dao.getCountByTypeAndStartTime(LogTypeEnum.RPC.getCode(), logMonitors.earlyStartTime());
    }

    @Override
    public List<TraceInfo> findLinkByTraceId(Long traceId) {
        ArrayList<TraceInfoDO> traceInfoByTraceId = dao.getTraceInfoByTraceId(traceId);
        return assembler.listToEntity(traceInfoByTraceId);
    }

    @Override
    public Page<TraceInfo> find(GetTraceInfoByArgAndPageRequest request) {
        List<Arg> args = request.args();
        args.add(new Arg("trace_id", "=", request.getTraceId()));
        args.add(new Arg("start_time", ">", request.getStartTime()));
        args.add(new Arg("type", "=", request.getType()));
        return find((Query) request);
    }

    @Override
    public Page<TraceDetailStatisticsView> findView(Query request) {
        List<TraceDetailStatisticsView> traceStatistics = dao.getTraceStatistics(request);
        Integer traceStatisticsCount = dao.getTraceStatisticsCount(request);
        return Page.build(traceStatistics, request.limit(), traceStatisticsCount);
    }
}
