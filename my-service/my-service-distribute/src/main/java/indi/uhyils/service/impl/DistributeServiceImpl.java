package indi.uhyils.service.impl;

import indi.uhyils.facade.DictFacade;
import indi.uhyils.facade.JvmFacade;
import indi.uhyils.pojo.DTO.response.JvmDataStatisticsDTO;
import indi.uhyils.pojo.DTO.response.JvmInfoLogDTO;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import indi.uhyils.pojo.DTO.response.welcome.AlgorithmStatisticsResponse;
import indi.uhyils.pojo.DTO.response.welcome.WelcomeResponse;
import indi.uhyils.service.DistributeService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月10日 08时25分
 */
@Service
public class DistributeServiceImpl implements DistributeService {


    @Autowired
    private JvmFacade jvmFacade;

    @Autowired
    private DictFacade dictFacade;

    @Override
    public WelcomeResponse getWelcomeData() {
        WelcomeResponse welcomeResponse = new WelcomeResponse();
        /* 统计信息 */
        JvmDataStatisticsDTO jvmDataStatisticsResponse = jvmFacade.jvmStatisticDate();
        welcomeResponse.setJvmDataStatisticsResponse(jvmDataStatisticsResponse);

        /*快捷启动*/
        QuickStartDTO quickStartResponse = dictFacade.quickStartInfo();
        welcomeResponse.setQuickStartResponse(quickStartResponse);

        /*jvm图表信息*/
        JvmInfoLogDTO jvmInfoLogResponse = jvmFacade.jvmInfoLog();
        welcomeResponse.setJvmInfoLogResponse(jvmInfoLogResponse);

        /*算法信息 还没有*/
        welcomeResponse.setAlgorithmStatisticsResponse(AlgorithmStatisticsResponse.build(new ArrayList<>()));

        /*版本信息*/
        VersionInfoDTO versionInfoResponse = dictFacade.versionInfo();
        welcomeResponse.setVersionInfoResponse(versionInfoResponse);

        /* 下一步计划 */
        LastPlanDTO lastPlanResponse = dictFacade.lastPlan();
        welcomeResponse.setLastPlanResponse(lastPlanResponse);
        return welcomeResponse;
    }
}
