package indi.uhyils.serviceImpl;

import indi.uhyils.dao.TraceDetailDao;
import indi.uhyils.pojo.model.TraceDetailDO;
import indi.uhyils.pojo.request.GetTraceDetailByHashCodeRequest;
import indi.uhyils.pojo.response.GetTraceDetailByHashCodeResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.TraceDetailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
@RpcService
public class TraceDetailServiceImpl extends BaseDefaultServiceImpl<TraceDetailDO> implements TraceDetailService {

    @Autowired
    private TraceDetailDao dao;


    @Override
    public TraceDetailDao getDao() {
        return dao;
    }

    public void setDao(TraceDetailDao dao) {
        this.dao = dao;
    }

    @Override
    public ServiceResult<GetTraceDetailByHashCodeResponse> getTraceDetailByHashCode(GetTraceDetailByHashCodeRequest request) {
        TraceDetailDO entity = dao.getTraceDetailByHashCode(request);
        return ServiceResult.buildSuccessResult(GetTraceDetailByHashCodeResponse.build(entity));
    }
}
