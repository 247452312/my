package indi.uhyils.repository;

import indi.uhyils.pojo.DO.TraceDetailStatisticsView;
import indi.uhyils.pojo.DO.TraceInfoDO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.cqe.query.BlackQuery;
import indi.uhyils.pojo.entity.OnlineMonitors;
import indi.uhyils.pojo.entity.Trace;
import indi.uhyils.pojo.entity.TraceInfo;
import indi.uhyils.repository.base.BaseEntityRepository;
import java.util.List;

/**
 * (TraceInfo)表 数据仓库层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
public interface TraceInfoRepository extends BaseEntityRepository<TraceInfoDO, TraceInfo> {


    /**
     * 根据traceId和rpcId获取链路信息
     *
     * @param trace
     *
     * @return
     */
    List<TraceInfo> findTraceInfoByTraceIdAndRpcId(Trace trace);

    /**
     * 获取前台请求次数
     *
     * @param logMonitors
     *
     * @return
     */
    Long findWebRequestCount(OnlineMonitors logMonitors);

    /**
     * 获取rpc总次数
     *
     * @param logMonitors
     *
     * @return
     */
    Long findRpcExecuteCount(OnlineMonitors logMonitors);

    /**
     * 根据traceId获取link
     *
     * @param traceId
     *
     * @return
     */
    List<TraceInfo> findLinkByTraceId(Long traceId);

    /**
     * 根据query获取
     *
     * @param request
     *
     * @return
     */
    Page<TraceInfo> find(GetTraceInfoByArgAndPageRequest request);

    /**
     * 视图获取
     *
     * @param request
     *
     * @return
     */
    Page<TraceDetailStatisticsView> findView(BlackQuery request);


    /**
     * 获取每秒网关并发数
     *
     * @return
     */
    Long findConcurrentNumber(Integer logType, Long startTime);

    /**
     * 获取服务降级当前等级
     *
     * @param defaultLevel
     *
     * @return
     */
    Long getRelegationLevel(Long defaultLevel);

}
