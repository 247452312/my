package indi.uhyils.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.DO.DbInfoDO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月17日 13时09分
 */
public final class DBManager {

    private static Map<DbTypeEnum, DbPool> dbPoolMap = new HashMap<>();


    /**
     * 连接
     *
     * @return
     */
    public static Connection connect(String uniqueId, DbInfoDO dbInfoDO) throws SQLException {
        DbTypeEnum paras = DbTypeEnum.parse(dbInfoDO.getType());
        switch (paras) {
            case MYSQL:

            case ORACLE:
            default:
                throw new SQLException("不支持的连接类型");
        }

    }

    /**
     * 数据库连接池
     */
    private static class DbPool {

        /**
         * 数据库类型
         */
        private DbTypeEnum dbTypeEnum;

        /**
         * 数据库连接池
         */
        private DataSource dataSource;


        public DbPool(DbTypeEnum dbTypeEnum, Properties properties) throws Exception {
            this.dbTypeEnum = dbTypeEnum;
            // 获取连接池对象
            this.dataSource = DruidDataSourceFactory.createDataSource(properties);
        }

        public DbPool(DbTypeEnum dbTypeEnum) throws Exception {
            Properties properties = new Properties();
            properties.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("initial-size", "1");
            properties.setProperty("max-active", "20");
            properties.setProperty("min-idle", "5");
            properties.setProperty("max-wait", "60000");
            properties.setProperty("pool-prepared-statements", "true");
            properties.setProperty("max-pool-prepared-statement-per-connection-size", "20");
            properties.setProperty("test-on-borrow", "false");
            properties.setProperty("test-while-idle", "true");
            properties.setProperty("time-between-eviction-runs-millis", "60000");
            properties.setProperty("min-evictable-idle-time-millis", "300000");
            properties.setProperty("filters", "stat,tableSqlFilter,dbLogFilter,rowsLimit,standard");
            properties.setProperty("use-global-data-source-stat", "true");
            properties.setProperty("connection-properties", "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");
            switch (dbTypeEnum) {
                case MYSQL:
                    properties.setProperty("validation-query", "select 'x'");
                    break;
                case ORACLE:
                    properties.setProperty("validation-query", "select 1 from dual");
                    break;
                default:
                    Asserts.assertTrue(false, "数据库类型暂不支持");
            }
            this.dbTypeEnum = dbTypeEnum;
            // 获取连接池对象
            this.dataSource = DruidDataSourceFactory.createDataSource(properties);
        }
    }
}
