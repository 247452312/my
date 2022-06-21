package indi.uhyils.facade.impl;

import indi.uhyils.annotation.Facade;
import indi.uhyils.context.UserInfoHelper;
import indi.uhyils.facade.JvmFacade;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.JvmDataStatisticsDTO;
import indi.uhyils.pojo.DTO.response.JvmInfoLogDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.protocol.rpc.LogMonitorProvider;
import indi.uhyils.rpc.annotation.RpcReference;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月10日 08时31分
 */
@Facade
public class JvmFacadeImpl implements JvmFacade {

    @RpcReference
    private LogMonitorProvider jvmProvider;

    @Override
    public JvmDataStatisticsDTO jvmStatisticDate() {
        DefaultCQE request = UserInfoHelper.makeCQE();
        ServiceResult<JvmDataStatisticsDTO> jvmDataStatisticsResponse = jvmProvider.getJvmDataStatisticsResponse(request);
        return jvmDataStatisticsResponse.validationAndGet();
    }

    @Override
    public JvmInfoLogDTO jvmInfoLog() {
        DefaultCQE request = UserInfoHelper.makeCQE();
        return jvmProvider.getJvmInfoLogResponse(request).validationAndGet();
    }
}
