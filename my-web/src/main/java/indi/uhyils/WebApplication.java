package indi.uhyils;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import indi.uhyils.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
@DubboComponentScan(basePackages = "indi.uhyils.*")
public class WebApplication {

    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(WebApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }

}
