package indi.uhyils.plan.config;

import indi.uhyils.plan.pojo.pool.SqlTableSourceBinaryTreePool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月31日 17时07分
 */
public class MysqlPlanConfiguration {


    @Value("${mysql.plan.table.pool.size:1000}")
    private Integer size;

    @Value("${mysql.allow-fault:true}")
    private Boolean allowFault;

    @Bean
    public SqlTableSourceBinaryTreePool createSqlTableSourceBinaryTreePool() {
        return new SqlTableSourceBinaryTreePool(size);
    }


    @Bean
    public MysqlPlanConfig config() {
        MysqlPlanConfig mysqlPlanConfig = new MysqlPlanConfig();
        mysqlPlanConfig.setAllowFault(allowFault);
        return mysqlPlanConfig;
    }


}
