package indi.uhyils.protocol.task;

import indi.uhyils.pojo.cqe.command.BlankCommand;
import indi.uhyils.service.TraceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 并发数统计
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月18日 06时28分
 */
@Component
public class ConcurrentNumberTask {


    @Autowired
    private TraceInfoService service;


    /**
     * 测试环境暂时关闭 todo
     */
    //    @Scheduled(cron = "*/2 * * * * ?")
    public void demoSchedule() {
        service.monitorConcurrentNumber(new BlankCommand());
    }
}
