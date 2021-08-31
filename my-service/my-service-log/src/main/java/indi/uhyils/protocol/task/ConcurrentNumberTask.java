package indi.uhyils.protocol.task;

import indi.uhyils.dao.TraceInfoDao;
import indi.uhyils.pojo.cqe.event.BlankEvent;
import indi.uhyils.protocol.rpc.DictProvider;
import indi.uhyils.rpc.annotation.RpcReference;
import indi.uhyils.service.TraceInfoService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 并发数统计
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 06时28分
 */
@Component
public class ConcurrentNumberTask {


    private static final Double RECOVERY_PRE = 0.8;

    /**
     * 低级服务是否降级(默认没有降级)
     */
    private volatile Boolean degradation = Boolean.FALSE;

    @Resource
    private TraceInfoDao traceInfoDao;

    @Autowired
    private TraceInfoService service;

    @RpcReference
    private DictProvider dictService;

    @Scheduled(cron = "*/2 * * * * ?")
    public void demoSchedule() {
        service.monitorConcurrentNumber(new BlankEvent());
    }
}
