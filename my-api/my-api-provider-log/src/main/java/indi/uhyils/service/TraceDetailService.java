package indi.uhyils.service;

import indi.uhyils.pojo.model.TraceDetailEntity;
import indi.uhyils.pojo.request.GetTraceDetailByHashCodeRequest;
import indi.uhyils.pojo.response.GetTraceDetailByHashCodeResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.service.base.DefaultEntityService;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月02日 08时14分
 */
public interface TraceDetailService extends DefaultEntityService<TraceDetailEntity> {

    /**
     * 获取链路详情
     *
     * @param request
     *
     * @return
     */
    ServiceResult<GetTraceDetailByHashCodeResponse> getTraceDetailByHashCode(GetTraceDetailByHashCodeRequest request);


}
