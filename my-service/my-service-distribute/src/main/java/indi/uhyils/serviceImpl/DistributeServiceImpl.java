package indi.uhyils.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.*;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.pojo.response.welcome.AlgorithmStatisticsResponse;
import indi.uhyils.pojo.response.welcome.WelcomeResponse;
import indi.uhyils.service.DistributeService;
import indi.uhyils.util.DistributeRpcApiUtil;
import indi.uhyils.rpc.annotation.RpcService;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@RpcService
public class DistributeServiceImpl implements DistributeService {


    @Override
    public ServiceResult<WelcomeResponse> getWelcomeData(DefaultRequest request) throws Exception {
        WelcomeResponse welcomeResponse = new WelcomeResponse();
        /* 统计信息 */
        ServiceResult<JSONObject> jsonObjectServiceResult = DistributeRpcApiUtil.defaultRequest(request.getUser(), "JvmService", "getJvmDataStatisticsResponse", request);
        if (!jsonObjectServiceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(jsonObjectServiceResult.getServiceMessage());
        }
        welcomeResponse.setJvmDataStatisticsResponse(jsonObjectServiceResult.getData().toJavaObject(JvmDataStatisticsResponse.class));

        /*快捷启动*/
        ServiceResult<JSONObject> quickStart = DistributeRpcApiUtil.defaultRequest(request.getUser(), "MenuService", "getQuickStartResponse", request);
        if (!quickStart.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(quickStart.getServiceMessage());
        }
        welcomeResponse.setQuickStartResponse(quickStart.getData().toJavaObject(QuickStartResponse.class));

        /*jvm图表信息*/
        ServiceResult<JSONObject> jvmInfoLog = DistributeRpcApiUtil.defaultRequest(request.getUser(), "JvmService", "getJvmInfoLogResponse", request);
        if (!jvmInfoLog.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(jvmInfoLog.getServiceMessage());
        }
        welcomeResponse.setJvmInfoLogResponse(jvmInfoLog.getData().toJavaObject(JvmInfoLogResponse.class));
        /*算法信息 还没有*/
        welcomeResponse.setAlgorithmStatisticsResponse(AlgorithmStatisticsResponse.build(new ArrayList<>()));

        /*版本信息*/
        ServiceResult<JSONObject> versionInfo = DistributeRpcApiUtil.defaultRequest(request.getUser(), "DictService", "getVersionInfoResponse", request);
        if (!versionInfo.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(versionInfo.getServiceMessage());
        }
        welcomeResponse.setVersionInfoResponse(versionInfo.getData().toJavaObject(VersionInfoResponse.class));

        /* 下一步计划 */
        ServiceResult<JSONObject> lastPlan = DistributeRpcApiUtil.defaultRequest(request.getUser(), "DictService", "getLastPlanResponse", request);
        if (!lastPlan.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(lastPlan.getServiceMessage());
        }
        welcomeResponse.setLastPlanResponse(lastPlan.getData().toJavaObject(LastPlanResponse.class));
        return ServiceResult.buildSuccessResult("首页查询成功", welcomeResponse, request);
    }
}
