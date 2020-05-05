package indi.uhyils;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import indi.uhyils.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月05日 13时22分
 */
@SpringBootApplication
@EnableDubbo
@DubboComponentScan(basePackages = "indi.uhyils.serviceImpl")
public class TransactionApplication {
    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(TransactionApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }
}
