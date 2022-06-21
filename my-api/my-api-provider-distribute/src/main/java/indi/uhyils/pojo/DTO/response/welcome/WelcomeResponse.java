package indi.uhyils.pojo.DTO.response.welcome;

import indi.uhyils.pojo.DTO.response.AlgorithmStatisticsResponse;
import indi.uhyils.pojo.DTO.response.JvmDataStatisticsDTO;
import indi.uhyils.pojo.DTO.response.JvmInfoLogDTO;
import indi.uhyils.pojo.DTO.response.LastPlanDTO;
import indi.uhyils.pojo.DTO.response.QuickStartDTO;
import indi.uhyils.pojo.DTO.response.VersionInfoDTO;
import java.io.Serializable;

/**
 * 首页初始化数据vo
 * <p>
 * 其中数据统计{@link JvmDataStatisticsDTO} 中携带质量详情,暂时
 * 前端无展示 其余都有展示
 * </p>
 * <p>
 * 快捷入口{@link QuickStartDTO} 返回值是从数据字典数据库中获取的
 * 值
 * </p>
 * <p>
 * JVM内存信息{@link JvmInfoLogDTO} 是项目初始化时通过MQ向JVM服务
 * 定时发送JVM信息 保存在数据库中
 * 算法统计{@link AlgorithmStatisticsResponse} 未完待续
 * 版本信息{@link VersionInfoDTO} 以及下一步计划{@link LastPlanDTO}
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
    private JvmDataStatisticsDTO jvmDataStatisticsResponse;


    /**
     * 首页中上 快捷入口
     */
    private QuickStartDTO quickStartResponse;

    /**
     * 首页中下 jvm内存信息
     */
    private JvmInfoLogDTO jvmInfoLogResponse;


    /**
     * 首页右上 算法统计
     */
    private AlgorithmStatisticsResponse algorithmStatisticsResponse;

    /**
     * 首页右中 版本信息
     */
    private VersionInfoDTO versionInfoResponse;


    /**
     * 首页右下 下一步计划
     */
    private LastPlanDTO lastPlanResponse;

    public JvmDataStatisticsDTO getJvmDataStatisticsResponse() {
        return jvmDataStatisticsResponse;
    }

    public void setJvmDataStatisticsResponse(JvmDataStatisticsDTO jvmDataStatisticsResponse) {
        this.jvmDataStatisticsResponse = jvmDataStatisticsResponse;
    }

    public QuickStartDTO getQuickStartResponse() {
        return quickStartResponse;
    }

    public void setQuickStartResponse(QuickStartDTO quickStartResponse) {
        this.quickStartResponse = quickStartResponse;
    }

    public JvmInfoLogDTO getJvmInfoLogResponse() {
        return jvmInfoLogResponse;
    }

    public void setJvmInfoLogResponse(JvmInfoLogDTO jvmInfoLogResponse) {
        this.jvmInfoLogResponse = jvmInfoLogResponse;
    }

    public AlgorithmStatisticsResponse getAlgorithmStatisticsResponse() {
        return algorithmStatisticsResponse;
    }

    public void setAlgorithmStatisticsResponse(AlgorithmStatisticsResponse algorithmStatisticsResponse) {
        this.algorithmStatisticsResponse = algorithmStatisticsResponse;
    }

    public VersionInfoDTO getVersionInfoResponse() {
        return versionInfoResponse;
    }

    public void setVersionInfoResponse(VersionInfoDTO versionInfoResponse) {
        this.versionInfoResponse = versionInfoResponse;
    }

    public LastPlanDTO getLastPlanResponse() {
        return lastPlanResponse;
    }

    public void setLastPlanResponse(LastPlanDTO lastPlanResponse) {
        this.lastPlanResponse = lastPlanResponse;
    }
}
