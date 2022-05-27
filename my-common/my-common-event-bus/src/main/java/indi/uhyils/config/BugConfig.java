package indi.uhyils.config;

import indi.uhyils.bus.Bus;
import indi.uhyils.bus.BusInterface;
import indi.uhyils.mq.util.MqUtil;
import indi.uhyils.protocol.register.base.Register;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 10时26分
 */
@Configuration
public class BugConfig {


    @Bean
    public BusInterface eventBus(List<Register> registers) throws IOException, TimeoutException {
        return MqUtil.addConsumer(BusInterface.BUS_EVENT_EXCHANGE_NAME, BusInterface.BUS_EVENT_QUEUE_NAME, BusInterface.BUS_EVENT_QUEUE_NAME, BusInterface.class, channel -> new Bus(channel, registers));
    }
}
