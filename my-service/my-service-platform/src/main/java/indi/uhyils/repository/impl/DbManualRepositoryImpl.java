package indi.uhyils.repository.impl;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import indi.uhyils.annotation.Repository;
import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.DO.DbInfoDO;
import indi.uhyils.pojo.entity.DbInfo;
import indi.uhyils.pojo.entity.ProviderInfo;
import indi.uhyils.pojo.entity.interfaces.InterfaceInfo;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DbInfoRepository;
import indi.uhyils.repository.DbManualRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.ExceptionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月28日 09时09分
 */
@Repository
public class DbManualRepositoryImpl implements DbManualRepository {

    @Autowired
    private DbInfoRepository dbInfoRepository;

    /**
     * 数据库连接池 key->providerId value->(key->数据库名称 value->数据库连接池)
     */
    private Map<Identifier, Map<String, DbInfoAndDataSource>> dbMap = new HashMap<>();

    /**
     * 初始化数据库连接池
     *
     * @param provider
     */
    private void initDbForConsumer(ProviderInfo provider) {
        if (dbMap.containsKey(provider.getUnique())) {
            return;
        }
        List<DbInfo> dbInfos = dbInfoRepository.findByProvider(provider);
        initDbForConsumer(provider.getUnique(), dbInfos);
    }

    /**
     * 初始化数据库连接池
     *
     * @param providerId
     */
    private void initDbForConsumer(Identifier providerId) {
        if (dbMap.containsKey(providerId)) {
            return;
        }
        List<DbInfo> dbInfos = dbInfoRepository.findByProviderId(providerId);
        initDbForConsumer(providerId, dbInfos);
    }

    /**
     * 初始化数据库连接池
     *
     * @param providerId
     */
    private void initDbForConsumer(Identifier providerId, List<DbInfo> dbInfo) {
        if (dbMap.containsKey(providerId)) {
            return;
        }
        Map<String, DbInfoAndDataSource> map = dbInfo.stream().collect(Collectors.toMap(t -> t.toData().getName(), DbInfoAndDataSource::new));
        dbMap.put(providerId, map);
    }


    @Override
    public Connection getConn(InterfaceInfo interfaceInfo) throws SQLException {
        DbInfo dbInfo = dbInfoRepository.findByInterfaceInfo(interfaceInfo);
        return getConn(dbInfo);
    }

    @Override
    public Connection getConn(DbInfo dbInfo) throws SQLException {
        DbInfoDO dbInfoDO = dbInfo.toData();
        String name = dbInfoDO.getName();
        Long providerId = dbInfoDO.getProviderId();
        initDbForConsumer(new Identifier(providerId));
        Map<String, DbInfoAndDataSource> providerDBMap = dbMap.get(providerId);
        Asserts.assertTrue(providerDBMap != null, "生产者数据库连接失败或无连接:{}", name);
        DbInfoAndDataSource dbInfoAndDataSource = providerDBMap.get(name);
        Asserts.assertTrue(dbInfoAndDataSource != null, "数据库连接失败或无连接:{}", name);
        return dbInfoAndDataSource.getDs().getConnection();
    }


    /**
     * 信息
     */
    private static class DbInfoAndDataSource {

        /**
         * 名称
         */
        private final String name;

        /**
         * 数据库连接
         */
        private final DataSource ds;

        /**
         * 数据库类型
         */
        private final DbTypeEnum dbTypeEnum;

        private DbInfoAndDataSource(String name, DataSource ds, DbTypeEnum dbTypeEnum) {
            this.name = name;
            this.ds = ds;
            this.dbTypeEnum = dbTypeEnum;
        }

        private DbInfoAndDataSource(DbInfo dbInfo) {
            this(dbInfo, makeDefaultProperties(dbInfo));
        }

        private DbInfoAndDataSource(DbInfo dbInfo, DataSource ds) {
            this(dbInfo.toData().getName(), ds, DbTypeEnum.parse(dbInfo.toData().getType()));
        }

        private DbInfoAndDataSource(DbInfo dbInfo, Properties properties) {
            this(dbInfo, initDataSource(properties));
        }

        private static DataSource initDataSource(Properties properties) {
            try {
                return DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e) {
                Asserts.assertTrue(false, ExceptionUtil.parseException(e));
                return null;
            }
        }

        /**
         * 获取默认的配置
         *
         * @param dbInfo
         *
         * @return
         */
        private static Properties makeDefaultProperties(DbInfo dbInfo) {
            DbInfoDO dbInfoDO = dbInfo.toData();
            DbTypeEnum parse = DbTypeEnum.parse(dbInfoDO.getType());
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
            switch (parse) {
                case MYSQL:
                    properties.setProperty("validation-query", "select 'x'");
                    break;
                case ORACLE:
                    properties.setProperty("validation-query", "select 1 from dual");
                    break;
                default:
                    Asserts.assertTrue(false, "数据库类型暂不支持");
            }
            return properties;
        }

        public String getName() {
            return name;
        }


        public DataSource getDs() {
            return ds;
        }

        public DbTypeEnum getDbTypeEnum() {
            return dbTypeEnum;
        }
    }

}
