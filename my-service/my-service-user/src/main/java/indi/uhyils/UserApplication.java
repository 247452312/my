package indi.uhyils;


import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import indi.uhyils.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 12时05分
 */
@SpringBootApplication
@EnableDubbo
@EnableTransactionManagement
@DubboComponentScan(basePackages = "indi.uhyils.serviceImpl")
public class UserApplication {
    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(UserApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }
}
