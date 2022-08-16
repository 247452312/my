package indi.uhyils.protocol.mysql.config;

import indi.uhyils.protocol.mysql.netty.MysqlNettyServer;
import indi.uhyils.protocol.mysql.netty.impl.MysqlNettyServerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月16日 14时41分
 */
@Configuration
public class MyMysqlConfig {

    @Bean
    public MysqlNettyServer mysqlNetty() {
        return new MysqlNettyServerImpl();
    }
}
