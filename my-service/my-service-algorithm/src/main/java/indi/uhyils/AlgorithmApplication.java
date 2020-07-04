package indi.uhyils;

import indi.uhyils.util.SpringUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月08日 13时56分
 */


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
public class AlgorithmApplication {
    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(AlgorithmApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }
}
