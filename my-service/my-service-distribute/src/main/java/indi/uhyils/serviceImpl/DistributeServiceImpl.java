package indi.uhyils.serviceImpl;

import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.JvmDataStatisticsResponse;
import indi.uhyils.pojo.response.JvmInfoLogResponse;
import indi.uhyils.pojo.response.LastPlanResponse;
import indi.uhyils.pojo.response.QuickStartResponse;
import indi.uhyils.pojo.response.VersionInfoResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.response.welcome.AlgorithmStatisticsResponse;
import indi.uhyils.pojo.response.welcome.WelcomeResponse;
import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.DictService;
import indi.uhyils.service.DistributeService;
import indi.uhyils.service.JvmService;
import indi.uhyils.service.MenuService;
import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
public class DistributeServiceImpl implements DistributeService {


    @RpcReference
    private MenuService menuService;

    @RpcReference
    private JvmService jvmService;

    @RpcReference
    private DictService dictService;

    @Override
    public ServiceResult<WelcomeResponse> getWelcomeData(DefaultRequest request) throws Exception {
        WelcomeResponse welcomeResponse = new WelcomeResponse();
        /* 统计信息 */
        ServiceResult<JvmDataStatisticsResponse> jvmDataStatisticsResponse = jvmService.getJvmDataStatisticsResponse(request);
        welcomeResponse.setJvmDataStatisticsResponse(jvmDataStatisticsResponse.validationAndGet());

        /*快捷启动*/
        ServiceResult<QuickStartResponse> quickStartResponse = menuService.getQuickStartResponse(request);
        welcomeResponse.setQuickStartResponse(quickStartResponse.validationAndGet());

        /*jvm图表信息*/
        ServiceResult<JvmInfoLogResponse> jvmInfoLogResponse = jvmService.getJvmInfoLogResponse(request);
        welcomeResponse.setJvmInfoLogResponse(jvmInfoLogResponse.validationAndGet());

        /*算法信息 还没有*/
        welcomeResponse.setAlgorithmStatisticsResponse(AlgorithmStatisticsResponse.build(new ArrayList<>()));

        /*版本信息*/
        ServiceResult<VersionInfoResponse> versionInfoResponse = dictService.getVersionInfoResponse(request);
        welcomeResponse.setVersionInfoResponse(versionInfoResponse.validationAndGet());

        /* 下一步计划 */
        ServiceResult<LastPlanResponse> lastPlanResponse = dictService.getLastPlanResponse(request);
        welcomeResponse.setLastPlanResponse(lastPlanResponse.validationAndGet());
        return ServiceResult.buildSuccessResult("首页查询成功", welcomeResponse);
    }
}
