package indi.uhyils.pojo.DTO.response.welcome;

import indi.uhyils.pojo.DTO.response.JvmDataStatisticsResponse;
import indi.uhyils.pojo.DTO.response.JvmInfoLogResponse;
import indi.uhyils.pojo.DTO.response.LastPlanResponse;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoResponse;
import java.io.Serializable;

/**
 * 首页初始化数据vo
 * <p>
 * 其中数据统计{@link JvmDataStatisticsResponse} 中携带质量详情,暂时
 * 前端无展示 其余都有展示
 * </p>
 * <p>
 * 快捷入口{@link QuickStartDTO} 返回值是从数据字典数据库中获取的
 * 值
 * </p>
 * <p>
 * JVM内存信息{@link JvmInfoLogResponse} 是项目初始化时通过MQ向JVM服务
 * 定时发送JVM信息 保存在数据库中
 * 算法统计{@link AlgorithmStatisticsResponse} 未完待续
 * 版本信息{@link VersionInfoResponse} 以及下一步计划{@link LastPlanResponse}
 * 均在数据字典数据库中获取
 * 以上5条组成了首页的展示具体位置见各个属性的注释
 * </p>
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
    private QuickStartDTO quickStartResponse;

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

    public QuickStartDTO getQuickStartResponse() {
        return quickStartResponse;
    }

    public void setQuickStartResponse(QuickStartDTO quickStartResponse) {
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
