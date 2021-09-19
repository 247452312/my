package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.repository.TraceInfoRepository;
import indi.uhyils.util.NacosUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 11时05分
 */
public class MonitorConcurrent extends AbstractEntity {

    /**
     * 恢复指数 如果为1 则实际恢复即为规定并发
     */
    private static final Double RECOVERY_PRE = 0.8;

    /**
     * 试试并发
     */
    private Long realTimeConcurrency;

    /**
     * 规定并发
     */
    private Long specifyConcurrency;

    private Boolean degradation;

    public MonitorConcurrent(Long realTimeConcurrency, Long specifyConcurrency) {
        this.realTimeConcurrency = realTimeConcurrency;
        this.specifyConcurrency = specifyConcurrency;
    }


    public boolean isDegradation(TraceInfoRepository rep) {
        if (degradation != null) {
            return degradation;
        }
        // 判断是否已经降级
        this.degradation = rep.findDegradationStatusInCache();
        if (this.degradation == null) {
            changeDegradation(false, rep);
        }
        return this.degradation;
    }

    public void changeDegradation(boolean degradation, TraceInfoRepository rep) {
        if (this.degradation == degradation) {
            return;
        }
        rep.changeDegradation(degradation);
        this.degradation = degradation;
    }

    /**
     * 同步降级状态到
     *
     * @param rep
     */
    public void syncDegradationStatus(TraceInfoRepository rep) {
        boolean degradation = isDegradation(rep);
        //如果已经降级过了
        if (degradation) {
            // 如果设置的并发数的80%大于已有并发数,说明高峰已经过去(设置80% 防抖)
            if (specifyConcurrency * RECOVERY_PRE > realTimeConcurrency) {
                changeDegradation(false, rep);
                NacosUtil.recoveryLowInterface();
            }
        } else {
            // 如果设置的并发数小于现有的并发数
            if (specifyConcurrency < realTimeConcurrency) {
                changeDegradation(true, rep);
                NacosUtil.degradationLowInterface();
            }
        }
    }
}
