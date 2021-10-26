package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.enum_.SqlTypeEnum;
import indi.uhyils.pojo.DO.DbInfoDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.DbInfoRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 数据库连接表(DbInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分07秒
 */
public class DbInfo extends SourceInfo<DbInfoDO> {

    private static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    @Default
    public DbInfo(DbInfoDO data) {
        super(data);
    }

    public DbInfo(Long id) {
        super(id, new DbInfoDO());
    }

    public DbInfo(Long id, DbInfoRepository rep) {
        super(id, new DbInfoDO());
        completion(rep);
    }

    public DbInfo(Identifier id) {
        super(id, new DbInfoDO());
    }

    @Override
    public Boolean testConnect() throws Exception {
        DbInfoDO dbInfoDO = toData();
        Asserts.assertTrue(dbInfoDO != null);
        DbTypeEnum paras = DbTypeEnum.parse(dbInfoDO.getType());
        Asserts.assertTrue(paras != null, "暂不支持数据库类型");
        switch (paras) {
            case ORACLE:
                return testConnect(dbInfoDO, ORACLE_DRIVER);
            case MYSQL:
                return testConnect(dbInfoDO, MYSQL_DRIVER);
            default:
                Asserts.assertTrue(false, "暂不支持数据库类型");
        }
        return false;
    }

    /**
     * 尝试连接数据库
     *
     * @param dbInfoDO
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    private Boolean testConnect(DbInfoDO dbInfoDO, String driver) throws ClassNotFoundException {
        Class.forName(driver);
        try {
            Connection con = DriverManager.getConnection(dbInfoDO.getUrl(), dbInfoDO.getUsername(), dbInfoDO.getPassword());
            con.close();
        } catch (SQLException e) {
            LogUtil.error(e, "数据库连接失败,url:{}", dbInfoDO.getUrl());
            return false;
        }
        return true;
    }

    /**
     * 创建DB
     *
     * @return
     */
    public Connection getConnect() {
        DbInfoDO dbInfoDO = toData();
        // todo 需要自定义数据库连接池
        return null;
    }

    /**
     * 执行sql语句并返回
     *
     * @param conn
     * @param map
     * @param consumerFilters
     *
     * @return
     */
    public List<Map<String, Object>> execute(Connection conn, Map<String, Object> map, List<ConsumerFilter> consumerFilters) throws SQLException {
        DbInfoDO dbInfoDO = toData();
        String sql = dbInfoDO.getSql();
        SqlTypeEnum sqlTypeEnum = typeForSql(sql);
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            sql = sql.replace("${" + key + "}", value.toString());
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            assert sqlTypeEnum != null;
            switch (sqlTypeEnum) {
                case DELETE:
                case INSERT:
                case UPDATE:
                    boolean execute = preparedStatement.execute();
                    ArrayList<Map<String, Object>> maps = new ArrayList<>(1);
                    HashMap<String, Object> e = new HashMap<>(1);
                    e.put("result", execute);
                    maps.add(e);
                    return maps;
                case SELECT:
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        return parseResultSetAndClose(resultSet, consumerFilters);
                    }
                default:
                    Asserts.assertTrue(false, "sql解析类型失败");
                    return Collections.emptyList();
            }
        }
    }

    /**
     * 解析resultSet
     *
     * @param ret             结果集
     * @param consumerFilters
     *
     * @return
     */
    private List<Map<String, Object>> parseResultSetAndClose(ResultSet ret, List<ConsumerFilter> consumerFilters) throws SQLException {
        // todo filter 获取
        List<Map<String, Object>> list = new ArrayList<>();
        ResultSetMetaData meta = ret.getMetaData();
        int cot = meta.getColumnCount();

        while (ret.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < cot; i++) {
                map.put(meta.getColumnName(i + 1), ret.getObject(i + 1));
            }
            list.add(map);
        }

        return list;
    }

    /**
     * 解析sql 到指定类型
     *
     * @param sql
     *
     * @return
     */
    private SqlTypeEnum typeForSql(String sql) {
        if (sql.trim().toLowerCase().startsWith("select")) {
            return SqlTypeEnum.SELECT;
        }
        if (sql.trim().toLowerCase().startsWith("update")) {
            return SqlTypeEnum.UPDATE;
        }
        if (sql.trim().toLowerCase().startsWith("insert")) {
            return SqlTypeEnum.INSERT;
        }
        if (sql.trim().toLowerCase().startsWith("delete")) {
            return SqlTypeEnum.DELETE;
        }
        Asserts.assertTrue(false, "sql类型识别失败, sql必须以select,update,insert,delete开头");
        return null;
    }


}
