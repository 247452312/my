package indi.uhyils.config;

import indi.uhyils.bus.Bus;
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
    public Bus eventBus() {
        return new Bus();
    }
}
