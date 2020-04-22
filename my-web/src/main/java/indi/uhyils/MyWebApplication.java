package indi.uhyils;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDubbo
@DubboComponentScan(basePackages = "indi.uhyils.*")
public class MyWebApplication {

    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(MyWebApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }

}
