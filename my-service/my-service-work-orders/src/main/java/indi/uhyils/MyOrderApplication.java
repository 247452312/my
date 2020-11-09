package indi.uhyils;

import indi.uhyils.util.SpringUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年10月27日 01时06分
 */
@SpringBootApplication
@EnableDubbo
@EnableTransactionManagement
public class MyOrderApplication {
    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(MyOrderApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }
}
