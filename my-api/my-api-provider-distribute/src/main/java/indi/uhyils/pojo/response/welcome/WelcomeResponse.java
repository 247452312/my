package indi.uhyils.pojo.response.welcome;

import indi.uhyils.pojo.response.*;

import java.io.Serializable;

/**
 * 首页初始化数据vo
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月21日 06时35分
 */
public class WelcomeResponse implements Serializable {

    /**
     * 首页左上 数据统计
     */
    private JvmDataStatisticsResponse jvmDataStatisticsResponse;


    /**
     * 首页中上 快捷入口
     */
    private QuickStartResponse quickStartResponse;

    /**
     * 首页中下 jvm内存信息
     */
    private JvmInfoLogResponse jvmInfoLogResponse;


    /**
     * 首页右上 算法统计
     */
    private AlgorithmStatisticsResponse algorithmStatisticsResponse;

    /**
     * 首页右中 版本信息
     */
    private VersionInfoResponse versionInfoResponse;


    /**
     * 首页右下 下一步计划
     */
    private LastPlanResponse lastPlanResponse;

    public JvmDataStatisticsResponse getJvmDataStatisticsResponse() {
        return jvmDataStatisticsResponse;
    }

    public void setJvmDataStatisticsResponse(JvmDataStatisticsResponse jvmDataStatisticsResponse) {
        this.jvmDataStatisticsResponse = jvmDataStatisticsResponse;
    }

    public QuickStartResponse getQuickStartResponse() {
        return quickStartResponse;
    }

    public void setQuickStartResponse(QuickStartResponse quickStartResponse) {
        this.quickStartResponse = quickStartResponse;
    }

    public JvmInfoLogResponse getJvmInfoLogResponse() {
        return jvmInfoLogResponse;
    }

    public void setJvmInfoLogResponse(JvmInfoLogResponse jvmInfoLogResponse) {
        this.jvmInfoLogResponse = jvmInfoLogResponse;
    }

    public AlgorithmStatisticsResponse getAlgorithmStatisticsResponse() {
        return algorithmStatisticsResponse;
    }

    public void setAlgorithmStatisticsResponse(AlgorithmStatisticsResponse algorithmStatisticsResponse) {
        this.algorithmStatisticsResponse = algorithmStatisticsResponse;
    }

    public VersionInfoResponse getVersionInfoResponse() {
        return versionInfoResponse;
    }

    public void setVersionInfoResponse(VersionInfoResponse versionInfoResponse) {
        this.versionInfoResponse = versionInfoResponse;
    }

    public LastPlanResponse getLastPlanResponse() {
        return lastPlanResponse;
    }

    public void setLastPlanResponse(LastPlanResponse lastPlanResponse) {
        this.lastPlanResponse = lastPlanResponse;
    }
}
