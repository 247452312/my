package indi.uhyils.serviceImpl;

import indi.uhyils.dao.TraceDetailDao;
import indi.uhyils.pojo.DO.TraceDetailDO;
import indi.uhyils.pojo.DTO.request.GetTraceDetailByHashCodeRequest;
import indi.uhyils.pojo.DTO.response.GetTraceDetailByHashCodeResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.BaseDefaultProvider;
import indi.uhyils.rpc.annotation.RpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
@RpcService
public class TraceDetailProvider extends BaseDefaultProvider<TraceDetailDO> implements indi.uhyils.protocol.rpc.provider.TraceDetailProvider {

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
