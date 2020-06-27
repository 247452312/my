package indi.uhyils;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import indi.uhyils.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;


/**
 * 前台项目启动类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 16时46分
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo(scanBasePackages = {"indi.uhyils.*.*"})
@DubboComponentScan(basePackages = "indi.uhyils.*")
public class WebApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext act = SpringApplication.run(WebApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }


}
