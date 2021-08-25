package indi.uhyils.protocol.rpc.provider;

import indi.uhyils.pojo.DO.TraceDetailDO;
import indi.uhyils.pojo.DTO.request.GetTraceDetailByHashCodeRequest;
import indi.uhyils.pojo.DTO.response.GetTraceDetailByHashCodeResponse;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.DefaultDTOProvider;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
public interface TraceDetailProvider extends DefaultDTOProvider<TraceDetailDO> {

    /**
     * 获取链路详情
     *
     * @param request
     *
     * @return
     */
    ServiceResult<GetTraceDetailByHashCodeResponse> getTraceDetailByHashCode(GetTraceDetailByHashCodeRequest request);


}
