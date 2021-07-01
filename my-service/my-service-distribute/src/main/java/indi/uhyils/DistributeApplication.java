package indi.uhyils;

import indi.uhyils.rpc.annotation.MyRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月08日 13时56分
 */


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MyRpc
@EnableTransactionManagement
public class DistributeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributeApplication.class, args);
    }
}
