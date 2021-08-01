package indi.uhyils.serviceImpl;

import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.factory.TraceInfoFactory;
import indi.uhyils.pojo.model.TraceInfoEntity;
import indi.uhyils.pojo.request.base.StringRequest;
import indi.uhyils.pojo.response.GetLinkByTraceIdResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.response.trace.OneTraceLink;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.TraceInfoService;
import java.util.List;
import java.util.Map;
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
    public ServiceResult<GetLinkByTraceIdResponse> getLinkByTraceId(StringRequest request) {
        List<TraceInfoEntity> traceInfoEntities = dao.getByTraceId(request.getValue());
        Map<Long, OneTraceLink> byTraceLink = TraceInfoFactory.createByTraceLink(traceInfoEntities);
        return ServiceResult.buildSuccessResult(GetLinkByTraceIdResponse.build(byTraceLink.get(Long.valueOf(request.getValue()))), request);
    }
}
