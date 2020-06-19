package indi.uhyils.config;

import indi.uhyils.exception.NoRabbitConfigException;
import indi.uhyils.pojo.rabbit.RabbitFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 初始化MqBean
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月19日 19时03分
 */
@Component
public class MqConfig {


    @Value("${rabbit.host}")
    private String host;
    @Value("${rabbit.port}")
    private Integer port;
    @Value("${rabbit.username}")
    private String username;
    @Value("${rabbit.password}")
    private String password;

    @Bean
    public RabbitFactory getRabbitFactory() {
        return RabbitFactory.getInstance(host, port, username, password);
    }

}
