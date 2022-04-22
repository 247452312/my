package indi.uhyils.pojo.entity;

import indi.uhyils.enum_.DemotionTypeEnum;
import indi.uhyils.facade.ServiceControlFacade;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.repository.RelegationRepository;
import indi.uhyils.repository.TraceInfoRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.LogUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 降级entity 聚合根为: 此次要降级的等级
 * <p>
 * 服务等级初始化为11 降级到n(0< n <11)时 会将 n-11 等级的服务降级
 * 恢复到x(0< x <11)时 会将0-x的服务恢复
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
     * 服务降级或者恢复操作间隔
     */
    private static final Long RELEGATION_OPTION_INTERVAL_TIME = 1000L * 60 * 5;

    /**
     * 当前的服务等级 初始化为11
     */
    private static final long LEVEL = 11;

    /**
     * 试试并发
     */
    private final Long realTimeConcurrency;

    /**
     * 规定并发
     */
    private final Long specifyConcurrency;

    /**
     * 操作类型
     */
    private final DemotionTypeEnum type;

    /**
     * 待操作的接口
     */
    private final List<Relegation> beDowngraded;

    /**
     * @param realTimeConcurrency 每秒网关的并发数
     * @param specifyConcurrency  字典中人工设置的自动降级的并发数
     */
    public MonitorConcurrent(TraceInfoRepository rep, RelegationRepository repository, Long realTimeConcurrency, Long specifyConcurrency) {
        super();
        initLevel(rep);
        this.realTimeConcurrency = realTimeConcurrency;
        this.specifyConcurrency = specifyConcurrency;
        this.type = initType();
        if (type != DemotionTypeEnum.NULL) {
            this.beDowngraded = repository.findDegradationLowInterfaceByLevel(getUnique());
        } else {
            this.beDowngraded = null;
        }
    }

    /**
     * 初始化类型,是降级还是升级
     *
     * @return
     */
    private DemotionTypeEnum initType() {
        /* 设置并发数的80% ~ 设置的并发数 之间的不会有其他的操作*/
        if (realTimeConcurrency >= specifyConcurrency * RECOVERY_PRE && realTimeConcurrency <= specifyConcurrency) {
            return DemotionTypeEnum.NULL;
        }
        // 如果设置的并发数的80%大于已有并发数,说明高峰已经过去(设置80% 防抖) 服务恢复
        if (specifyConcurrency * RECOVERY_PRE > realTimeConcurrency) {
            return DemotionTypeEnum.DOWN;
        }
        //如果设置的并发数小于现有的并发数 服务降级
        if (specifyConcurrency < realTimeConcurrency) {
            return DemotionTypeEnum.RECOVER;
        }
        Asserts.throwException("错误,并发数有问题,设定并发数为:{},(80%:{}) 实际并发数为:{}", specifyConcurrency, specifyConcurrency * 0.8, realTimeConcurrency);
        return null;
    }

    /**
     * 同步降级状态到
     *
     * @param repository
     * @param facade
     */
    public void syncDegradationStatus(RelegationRepository repository, ServiceControlFacade facade) {

        Asserts.assertTrue(type != null);
        // 不操作
        if (type == DemotionTypeEnum.NULL) {
            return;
        }
        // 没有查询到操作接口,不进行操作
        if (CollectionUtil.isEmpty(beDowngraded)) {
            return;
        }
        // 记录是否进行了操作
        boolean option = false;
        synchronized (MonitorConcurrent.class) {
            if (type == DemotionTypeEnum.RECOVER) {
                // 如果已经恢复到正常等级,则不需要进行恢复
                if (getUnique() == LEVEL) {
                    return;
                }
                // 恢复
                option = recoveryLowInterface(facade);
            } else if (type == DemotionTypeEnum.DOWN) {
                // 现在已经降级到最终节点,不能再次降级
                if (getUnique() == 0) {
                    LogUtil.error("服务降级已经降级到最后节点,不能再次进行降级");
                    return;
                }
                // 服务降级时需要关心时间间隔,但是服务恢复不需要,可以恢复就立刻恢复
                boolean canOption = repository.checkRelegationOptionTime();
                // 时间还没到
                if (!canOption) {
                    return;
                }
                // 降级
                option = degradationLowInterface(facade);
            }
        }

        if (option) {
            // 如果进行了服务降级或者服务恢复操作,则记录时间,5分钟内不得再次降级或者恢复
            repository.markRelegationOptionTime(RELEGATION_OPTION_INTERVAL_TIME);
        }
    }

    /**
     * 降级
     *
     * @param facade
     *
     * @return
     */
    private boolean degradationLowInterface(ServiceControlFacade facade) {
        // 挨个降级
        List<Boolean> list = this.beDowngraded.stream().map(t -> t.demotion(facade)).collect(Collectors.toList());
        // 只要有一个成功,则代表此次操作成功
        return list.stream().anyMatch(t -> t);
    }

    /**
     * 恢复降级
     *
     * @param facade
     *
     * @return
     */
    private boolean recoveryLowInterface(ServiceControlFacade facade) {
        List<Boolean> list = this.beDowngraded.stream().map(t -> t.recover(facade)).collect(Collectors.toList());
        return list.stream().anyMatch(t -> t);
    }

    /**
     * 初始化当前服务等级
     *
     * @param rep
     */
    private void initLevel(TraceInfoRepository rep) {
        Long level = rep.getRelegationLevel(LEVEL);
        setUnique(level);
    }
}
