package indi.uhyils;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 12时05分
 */
@SpringBootApplication
@EnableTransactionManagement
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
