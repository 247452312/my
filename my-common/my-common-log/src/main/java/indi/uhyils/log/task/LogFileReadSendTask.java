package indi.uhyils.log.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月29日 14时42分
 */
@Component
public class LogFileReadSendTask {

    @Scheduled(cron = "0 */1 * * * ?")
    public void demoSchedule() {

    }

}
