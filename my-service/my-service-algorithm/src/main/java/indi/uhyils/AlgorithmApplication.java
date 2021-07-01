package indi.uhyils;

import indi.uhyils.rpc.annotation.MyRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月08日 13时56分
 */


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MyRpc
public class AlgorithmApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlgorithmApplication.class, args);
    }
}
