package indi.uhyils;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import indi.uhyils.util.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 12时05分
 */
@SpringBootApplication
@EnableDubbo
@DubboComponentScan(basePackages = "indi.uhyils.serviceImpl")
@MapperScan("indi.uhyils.dao")
@ImportResource(value = {"classpath:dubbo.xml"})
public class ClassApplication {
    public static void main(String[] args) {
        ApplicationContext act = SpringApplication.run(ClassApplication.class, args);
        SpringUtil.setApplicationContext(act);
    }
}
