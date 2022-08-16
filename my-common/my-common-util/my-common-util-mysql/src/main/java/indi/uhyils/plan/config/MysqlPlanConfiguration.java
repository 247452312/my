package indi.uhyils.plan.config;

import indi.uhyils.plan.pojo.pool.SqlTableSourceBinaryTreePool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月31日 17时07分
 */
@Configuration
public class MysqlPlanConfiguration {


    @Value("${mysql.plan.table.pool.size:1000}")
    private Integer size;

    @Bean
    public SqlTableSourceBinaryTreePool createSqlTableSourceBinaryTreePool() {
        return new SqlTableSourceBinaryTreePool(size);
    }

}
