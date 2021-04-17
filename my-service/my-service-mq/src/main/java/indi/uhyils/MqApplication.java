package indi.uhyils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import indi.uhyils.rpc.annotation.MyRpc;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月08日 13时56分
 */
@SpringBootApplication
@MyRpc
public class MqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class, args);
    }
}
