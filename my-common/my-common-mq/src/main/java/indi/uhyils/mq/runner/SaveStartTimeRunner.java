package indi.uhyils.mq.runner;

import indi.uhyils.mq.content.RabbitMqContent;
import indi.uhyils.util.LogUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化ip和程序启动时间
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 09时58分
 */
@Component
@Order(1)
public class SaveStartTimeRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        RabbitMqContent.init();
        LogUtil.info(this, "MQ日志初始化成功");
    }
}
