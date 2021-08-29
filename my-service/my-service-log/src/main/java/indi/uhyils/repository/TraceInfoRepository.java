package indi.uhyils.repository;

import indi.uhyils.pojo.DO.TraceInfoDO;
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
}
