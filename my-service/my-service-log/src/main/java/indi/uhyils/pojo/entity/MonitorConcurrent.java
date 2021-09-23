package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.repository.TraceInfoRepository;

/**
 * 降级entity 聚合根为: 此次要降级的等级
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 11时05分
 */
public class MonitorConcurrent extends AbstractEntity<Long> {

    /**
     * 恢复指数 如果为1 则实际恢复即为规定并发
     */
    private static final Float RECOVERY_PRE = 0.8f;

    /**
     * 降级最小间隔时间(5分钟)
     */
    private static final Long MINIMUM_INTERVAL = 5 * 60 * 1000L;

    /**
     * 当前的服务等级 初始化为11
     */
    private volatile static long LEVEL = 11;

    /**
     * 上次降级或恢复的时间
     */
    private volatile static Long DATE = System.currentTimeMillis();

    /**
     * 试试并发
     */
    private Long realTimeConcurrency;

    /**
     * 规定并发
     */
    private Long specifyConcurrency;

    private Boolean degradation;

    /**
     * @param realTimeConcurrency 每秒网关的并发数
     * @param specifyConcurrency  字典中人工设置的自动降级的并发数
     */
    public MonitorConcurrent(Long realTimeConcurrency, Long specifyConcurrency) {
        super(LEVEL - 1);
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
                recoveryLowInterface();
            }
        } else {
            // 如果设置的并发数小于现有的并发数
            if (specifyConcurrency < realTimeConcurrency) {
                changeDegradation(true, rep);
                degradationLowInterface();
            }
        }
    }


    /**
     * 降级
     */
    private void degradationLowInterface() {
        /*
        todo 降级
        1.要先知道哪些服务对应的降级等级是什么
        2.时间是否到降级的最小间隔时间
         */

    }

    /**
     * 恢复降级
     */
    private void recoveryLowInterface() {
        /*
        todo 恢复降级
        1.要先知道哪些服务对应的降级等级是什么
        2.不需要关心时间
        3.恢复等级为unique+1
         */
    }
}
