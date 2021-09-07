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
import indi.uhyils.pojo.cqe.query.BlackQuery;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.entity.OnlineMonitors;
import indi.uhyils.pojo.entity.Trace;
import indi.uhyils.pojo.entity.TraceInfo;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.redis.Redisable;
import indi.uhyils.repository.TraceInfoRepository;
import indi.uhyils.repository.base.AbstractRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * (TraceInfo)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
@Repository
public class TraceInfoRepositoryImpl extends AbstractRepository<TraceInfo, TraceInfoDO, TraceInfoDao, TraceInfoDTO, TraceInfoAssembler> implements TraceInfoRepository {

    /**
     * 服务降级key
     */
    private static final String DEGRADATION_STATUS = "degradation";

    @Autowired
    private RedisPoolHandle redis;

    protected TraceInfoRepositoryImpl(TraceInfoAssembler convert, TraceInfoDao dao) {
        super(convert, dao);
    }

    @Override
    public List<TraceInfo> findTraceInfoByTraceIdAndRpcId(Trace trace) {
        List<TraceInfoDO> traceInfos = dao.getLinkByTraceIdAndRpcIdPrefix(trace.traceId(), trace.rpcId());
        return assembler.listToEntity(traceInfos);
    }

    @Override
    public Long findWebRequestCount(OnlineMonitors logMonitors) {
        return dao.getCountByTypeAndStartTime(LogTypeEnum.CONTROLLER.getCode(), logMonitors.earlyStartTime());
    }

    @Override
    public Long findRpcExecuteCount(OnlineMonitors logMonitors) {
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
        List<Arg> args = request.getArgs();
        args.add(new Arg("trace_id", "=", request.getTraceId()));
        args.add(new Arg("start_time", ">", request.getStartTime()));
        args.add(new Arg("type", "=", request.getType()));
        return find(args, request.getOrder(), request.getLimit());
    }

    @Override
    public Page<TraceDetailStatisticsView> findView(BlackQuery request) {
        List<TraceDetailStatisticsView> traceStatistics = dao.getTraceStatistics(request);
        Integer traceStatisticsCount = dao.getTraceStatisticsCount(request);
        return Page.build(traceStatistics, request.getLimit(), traceStatisticsCount);
    }

    @Override
    public Long findConcurrentNumber(Integer logType, Long startTime) {
        return dao.getCountByTypeAndStartTime(logType, startTime);
    }

    @Override
    public Boolean findDegradationStatusInCache() {
        try (Redisable jedis = redis.getJedis()) {
            String s = jedis.get(DEGRADATION_STATUS);
            return "true".equals(s);
        }
    }

    @Override
    public void changeDegradation(boolean degradation) {
        try (Redisable jedis = redis.getJedis()) {
            jedis.set(DEGRADATION_STATUS, degradation ? "true" : "false");
        }
    }
}
