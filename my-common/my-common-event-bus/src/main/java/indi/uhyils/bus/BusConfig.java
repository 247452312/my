package indi.uhyils.bus;

import indi.uhyils.bus.listener.EventListener;
import java.util.List;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月12日 17时45分
 */
@Configurable
public class BusConfig {

    @Bean
    public BusEventManager bugEventManager(List<EventListener> listeners) {
        return new BusEventManager(listeners);
    }
}
