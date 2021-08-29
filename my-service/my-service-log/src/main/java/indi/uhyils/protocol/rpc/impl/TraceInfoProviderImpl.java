package indi.uhyils.protocol.rpc.impl;

import indi.uhyils.pojo.DTO.TraceDetailStatisticsDTO;
import indi.uhyils.pojo.DTO.TraceInfoDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.request.GetLinkByTraceIdAndRpcIdQuery;
import indi.uhyils.pojo.DTO.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.DTO.response.TraceInfosDTO;
import indi.uhyils.pojo.DTO.response.GetLogTypeResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.BaseQuery;
import indi.uhyils.pojo.cqe.query.TraceIdQuery;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.protocol.rpc.provider.TraceInfoProvider;
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
    protected BaseDoService<TraceInfoDTO> getService() {
        return service;
    }

    @Override
    public ServiceResult<TraceInfosDTO> getLinkByTraceIdAndRpcId(GetLinkByTraceIdAndRpcIdQuery request) {
        return null;
    }

    @Override
    public ServiceResult<List<TraceInfoDTO>> getLinkByTraceId(TraceIdQuery request) {
        return null;
    }

    @Override
    public ServiceResult<Page<TraceInfoDTO>> getTraceInfoByArgAndPage(GetTraceInfoByArgAndPageRequest request) {
        return null;
    }

    @Override
    public ServiceResult<Page<TraceDetailStatisticsDTO>> getTraceStatistics(BaseQuery request) {
        return null;
    }

    @Override
    public ServiceResult<List<GetLogTypeResponse>> getLogType(DefaultCQE request) {
        return null;
    }
}

