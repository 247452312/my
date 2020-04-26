package indi.uhyils;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import indi.uhyils.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月26日 09时40分
 */
@SpringBootApplication
@EnableDubbo
@DubboComponentScan(basePackages = "indi.uhyils.serviceImpl")
@ImportResource(value = {"classpath:dubbo.xml"})
public class TokenApplication {
    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(TokenApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }
}
