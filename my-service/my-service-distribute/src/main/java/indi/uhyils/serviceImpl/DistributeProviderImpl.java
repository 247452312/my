package indi.uhyils.serviceImpl;

import indi.uhyils.pojo.DTO.request.base.DefaultRequest;
import indi.uhyils.pojo.DTO.response.JvmDataStatisticsDTO;
import indi.uhyils.pojo.DTO.response.JvmInfoLogDTO;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.pojo.DTO.response.welcome.AlgorithmStatisticsResponse;
import indi.uhyils.pojo.DTO.response.welcome.WelcomeResponse;
import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.protocol.rpc.provider.DictProvider;
import indi.uhyils.protocol.rpc.provider.DistributeProvider;
import indi.uhyils.protocol.rpc.provider.JvmProvider;
import indi.uhyils.protocol.rpc.provider.MenuProvider;
import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
public class DistributeProviderImpl implements DistributeProvider {


    @RpcReference
    private MenuProvider menuService;

    @RpcReference
    private JvmProvider jvmService;

    @RpcReference
    private DictProvider dictService;

    @Override
    public ServiceResult<WelcomeResponse> getWelcomeData(DefaultCQE request) throws Exception {
        WelcomeResponse welcomeResponse = new WelcomeResponse();
        /* 统计信息 */
        ServiceResult<JvmDataStatisticsDTO> jvmDataStatisticsResponse = jvmService.getJvmDataStatisticsResponse(request);
        welcomeResponse.setJvmDataStatisticsResponse(jvmDataStatisticsResponse.validationAndGet());

        /*快捷启动*/
        ServiceResult<QuickStartDTO> quickStartResponse = menuService.getQuickStartResponse(request);
        welcomeResponse.setQuickStartResponse(quickStartResponse.validationAndGet());

        /*jvm图表信息*/
        ServiceResult<JvmInfoLogDTO> jvmInfoLogResponse = jvmService.getJvmInfoLogResponse(request);
        welcomeResponse.setJvmInfoLogResponse(jvmInfoLogResponse.validationAndGet());

        /*算法信息 还没有*/
        welcomeResponse.setAlgorithmStatisticsResponse(AlgorithmStatisticsResponse.build(new ArrayList<>()));

        /*版本信息*/
        ServiceResult<VersionInfoDTO> versionInfoResponse = dictService.getVersionInfoResponse(request);
        welcomeResponse.setVersionInfoResponse(versionInfoResponse.validationAndGet());

        /* 下一步计划 */
        ServiceResult<LastPlanDTO> lastPlanResponse = dictService.getLastPlanResponse(request);
        welcomeResponse.setLastPlanResponse(lastPlanResponse.validationAndGet());
        return ServiceResult.buildSuccessResult("首页查询成功", welcomeResponse);
    }
}
