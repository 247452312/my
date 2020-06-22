package indi.uhyils.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.response.*;
import indi.uhyils.pojo.response.welcome.AlgorithmStatisticsResponse;
import indi.uhyils.pojo.response.welcome.WelcomeResponse;
import indi.uhyils.service.DistributeService;
import indi.uhyils.util.DistributeDubboApiUtil;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 16时28分
 */
@Service(group = "${spring.profiles.active}")
public class DistributeServiceImpl implements DistributeService {


    @Override
    public ServiceResult<WelcomeResponse> getWelcomeData(DefaultRequest request) throws Exception {
        WelcomeResponse welcomeResponse = new WelcomeResponse();
        /* 统计信息 */
        ServiceResult<JSONObject> jsonObjectServiceResult = DistributeDubboApiUtil.defaultRequest("JvmService", "getJvmDataStatisticsResponse", request);
        if (!jsonObjectServiceResult.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(jsonObjectServiceResult.getServiceMessage());
        }
        welcomeResponse.setJvmDataStatisticsResponse(jsonObjectServiceResult.getData().toJavaObject(JvmDataStatisticsResponse.class));

        /*快捷启动*/
        ServiceResult<JSONObject> quickStart = DistributeDubboApiUtil.defaultRequest("MenuService", "getQuickStartResponse", request);
        if (!quickStart.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(quickStart.getServiceMessage());
        }
        welcomeResponse.setQuickStartResponse(quickStart.getData().toJavaObject(QuickStartResponse.class));

        /*jvm图表信息*/
        ServiceResult<JSONObject> jvmInfoLog = DistributeDubboApiUtil.defaultRequest("JvmService", "getJvmInfoLogResponse", request);
        if (!jvmInfoLog.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(jvmInfoLog.getServiceMessage());
        }
        welcomeResponse.setJvmInfoLogResponse(jvmInfoLog.getData().toJavaObject(JvmInfoLogResponse.class));
        /*算法信息 还没有*/
        welcomeResponse.setAlgorithmStatisticsResponse(AlgorithmStatisticsResponse.build(new ArrayList<>()));

        /*版本状态*/
        ServiceResult<JSONObject> versionInfo = DistributeDubboApiUtil.defaultRequest("DictService", "getVersionInfoResponse", request);
        if (!versionInfo.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(versionInfo.getServiceMessage());
        }
        welcomeResponse.setVersionInfoResponse(versionInfo.getData().toJavaObject(VersionInfoResponse.class));

        /* 下一步计划 */
        ServiceResult<JSONObject> lastPlan = DistributeDubboApiUtil.defaultRequest("DictService", "getLastPlanResponse", request);
        if (!lastPlan.getServiceCode().equals(ServiceCode.SUCCESS.getText())) {
            throw new Exception(lastPlan.getServiceMessage());
        }
        welcomeResponse.setLastPlanResponse(lastPlan.getData().toJavaObject(LastPlanResponse.class));
        return ServiceResult.buildSuccessResult("首页查询成功", welcomeResponse, request);
    }
}
