package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.TraceInfoAssembler;
import indi.uhyils.enum_.LogTypeEnum;
import indi.uhyils.pojo.DO.TraceDetailStatisticsView;
import indi.uhyils.pojo.DO.TraceInfoDO;
import indi.uhyils.pojo.DTO.TraceDetailStatisticsDTO;
import indi.uhyils.pojo.DTO.TraceInfoDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.DTO.request.GetLinkByTraceIdAndRpcIdQuery;
import indi.uhyils.pojo.DTO.request.GetTraceInfoByArgAndPageRequest;
import indi.uhyils.pojo.DTO.response.GetLogTypeResponse;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.cqe.query.Query;
import indi.uhyils.pojo.cqe.query.TraceIdQuery;
import indi.uhyils.pojo.entity.Trace;
import indi.uhyils.pojo.entity.TraceInfo;
import indi.uhyils.repository.TraceInfoRepository;
import indi.uhyils.service.TraceInfoService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * (TraceInfo)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分55秒
 */
@Service
@ReadWriteMark(tables = {"sys_trace_info"})
public class TraceInfoServiceImpl extends AbstractDoService<TraceInfoDO, TraceInfo, TraceInfoDTO, TraceInfoRepository, TraceInfoAssembler> implements TraceInfoService {

    public TraceInfoServiceImpl(TraceInfoAssembler assembler, TraceInfoRepository repository) {
        super(assembler, repository);
    }

    @Override
    public List<TraceInfoDTO> getLinkByTraceIdAndRpcId(GetLinkByTraceIdAndRpcIdQuery request) {
        Trace trace = new Trace(request.getTraceId(), request.getRpcId());
        List<TraceInfo> lists = trace.findTraceInfoByTraceIdAndRpcId(rep);
        return assem.listToDTO(lists);
    }

    @Override
    public List<TraceInfoDTO> getLinkByTraceId(TraceIdQuery request) {
        List<TraceInfoDO> list = dao.getTraceInfoByTraceId(request.getTraceId());
        return list.stream().map(assem::toDTO).collect(Collectors.toList());
    }

    @Override
    public Page<TraceInfoDTO> getTraceInfoByArgAndPage(GetTraceInfoByArgAndPageRequest request) {
        List<TraceInfoDO> list = dao.getTraceInfoByArgAndPage(request);
        Integer count = dao.getTraceInfoByArgAndPageCount(request);
        Page<TraceInfoDO> build = Page.build(request, list, count);
        return assem.pageToDTO(build);
    }

    @Override
    public Page<TraceDetailStatisticsDTO> getTraceStatistics(Query request) {
        List<TraceDetailStatisticsView> list = dao.getTraceStatistics(request);
        List<TraceDetailStatisticsDTO> collect = list.stream().map(assem::viewToDTO).collect(Collectors.toList());
        Integer count = dao.getTraceStatisticsCount(request);
        return Page.build(request, collect, count);
    }

    @Override
    public ArrayList<GetLogTypeResponse> getLogType(DefaultCQE request) {
        ArrayList<GetLogTypeResponse> list = new ArrayList<>();
        for (LogTypeEnum value : LogTypeEnum.values()) {
            GetLogTypeResponse build = GetLogTypeResponse.build(value.name(), value.getCode());
            list.add(build);
        }
        return list;
    }

}
