package indi.uhyils;

import indi.uhyils.rpc.annotation.MyRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * 前台项目启动类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 16时46分
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MyRpc(baseScanPackage = {"indi.uhyils.*.*"})
public class WebApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }


}
