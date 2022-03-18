package indi.uhyils.config;

import indi.uhyils.pojo.MysqlServerInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月06日 11时12分
 */
@Configuration
public class MysqlConfig {

    @Value("mysql.db-name:root")
    private String dbName;

    @Bean
    public MysqlServerInfo mysqlServerInfo() {
        MysqlServerInfo mysqlServerInfo = new MysqlServerInfo();
        mysqlServerInfo.setDbName(dbName);
        return mysqlServerInfo;
    }
}
