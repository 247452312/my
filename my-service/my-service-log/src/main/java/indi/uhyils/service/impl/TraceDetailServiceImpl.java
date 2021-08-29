package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.TraceDetailAssembler;
import indi.uhyils.dao.TraceDetailDao;
import indi.uhyils.pojo.DO.TraceDetailDO;
import indi.uhyils.pojo.DTO.TraceDetailDTO;
import indi.uhyils.pojo.DTO.request.GetTraceDetailByHashCodeRequest;
import indi.uhyils.pojo.DTO.response.GetTraceDetailByHashCodeResponse;
import indi.uhyils.pojo.entity.TraceDetail;
import indi.uhyils.repository.TraceDetailRepository;
import indi.uhyils.service.TraceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (TraceDetail)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 16时58分54秒
 */
@Service
@ReadWriteMark(tables = {"sys_trace_detail"})
public class TraceDetailServiceImpl extends AbstractDoService<TraceDetailDO, TraceDetail, TraceDetailDTO, TraceDetailRepository, TraceDetailAssembler> implements TraceDetailService {

    @Autowired
    private TraceDetailDao dao;


    public TraceDetailServiceImpl(TraceDetailAssembler assembler, TraceDetailRepository repository) {
        super(assembler, repository);
    }

    @Override
    public GetTraceDetailByHashCodeResponse getTraceDetailByHashCode(GetTraceDetailByHashCodeRequest request) {
        TraceDetailDO entity = dao.getTraceDetailByHashCode(request);
        TraceDetailDTO traceDetailDTO = assem.toDTO(entity);
        return GetTraceDetailByHashCodeResponse.build(traceDetailDTO);
    }


}
