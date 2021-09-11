package indi.uhyils.facade.impl;

import indi.uhyils.annotation.Facade;
import indi.uhyils.context.UserContext;
import indi.uhyils.facade.DictFacade;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import indi.uhyils.protocol.rpc.DictProvider;
import indi.uhyils.rpc.annotation.RpcReference;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月10日 08时31分
 */
@Facade
public class DictFacadeImpl implements DictFacade {

    @RpcReference
    private DictProvider provider;

    @Override
    public QuickStartDTO quickStartInfo() {
        ServiceResult<QuickStartDTO> quickStartResponse = provider.getQuickStartResponse(UserContext.makeCQE());
        return quickStartResponse.validationAndGet();
    }

    @Override
    public VersionInfoDTO versionInfo() {
        ServiceResult<VersionInfoDTO> versionInfoResponse = provider.getVersionInfoResponse(UserContext.makeCQE());
        return versionInfoResponse.validationAndGet();
    }

    @Override
    public LastPlanDTO lastPlan() {
        ServiceResult<LastPlanDTO> lastPlanResponse = provider.getLastPlanResponse(UserContext.makeCQE());
        return lastPlanResponse.validationAndGet();
    }

}