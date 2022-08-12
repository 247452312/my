package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.TraceDetailStatisticsDTO;
import indi.uhyils.pojo.DTO.TraceInfoDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetLinkByTraceIdAndRpcIdQuery;
import indi.uhyils.pojo.DTO.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.DTO.response.LogTypeDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.BlackQuery;
import indi.uhyils.pojo.cqe.query.TraceIdQuery;
import indi.uhyils.protocol.rpc.TraceInfoProvider;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.service.TraceInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (TraceInfo)表 RPC对外访问实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
@RpcService
public class TraceInfoProviderImpl extends BaseDefaultProvider<TraceInfoDTO> implements TraceInfoProvider {


    @Autowired
    private TraceInfoService service;

    @Override
    public List<TraceInfoDTO> getLinkByTraceIdAndRpcId(GetLinkByTraceIdAndRpcIdQuery request) {
        return service.getLinkByTraceIdAndRpcId(request);
    }

    @Override
    public List<TraceInfoDTO> getLinkByTraceId(TraceIdQuery request) {
        return service.getLinkByTraceId(request);
    }

    @Override
    public Page<TraceInfoDTO> getTraceInfoByArgAndPage(GetTraceInfoByArgAndPageRequest request) {
        return service.getTraceInfoByArgAndPage(request);
    }

    @Override
    public Page<TraceDetailStatisticsDTO> getTraceStatistics(BlackQuery request) {
        return service.getTraceStatistics(request);
    }

    @Override
    public List<LogTypeDTO> getLogType(DefaultCQE request) {
        return service.getLogType(request);
    }

    @Override
    protected BaseDoService<TraceInfoDTO> getService() {
        return service;
    }
}

