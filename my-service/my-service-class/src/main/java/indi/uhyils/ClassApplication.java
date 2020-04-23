package indi.uhyils;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import indi.uhyils.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 12时05分
 */
@SpringBootApplication
@EnableDubbo
@DubboComponentScan(basePackages = "indi.uhyils.serviceImpl")
public class ClassApplication {
    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(ClassApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }
}
