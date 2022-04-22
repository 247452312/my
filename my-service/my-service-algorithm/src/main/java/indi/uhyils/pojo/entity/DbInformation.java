package indi.uhyils.pojo.entity;

import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.DTO.request.DbInformationDTO;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.pojo.tool.ColumnInfo;
import indi.uhyils.pojo.tool.TableInfo;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.KproUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;
import indi.uhyils.util.StringUtil;
import indi.uhyils.util.kpro.KproStringUtil;
import indi.uhyils.util.kpro.TypeConvertor;
import indi.uhyils.util.kpro.TypeConvertorFactory;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import org.apache.velocity.VelocityContext;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月10日 15时01分
 */
public class DbInformation extends AbstractEntity {

    /**
     * package参数路径
     */
    private static final String PACKAGE_PROPERTIES_PATH = "vm.package";

    /**
     * 默认package参数
     */
    private static final String PACKAGE_DEFAULT_PATH = "indi.uhyils";

    /**
     * author参数路径
     */
    private static final String AUTHOR_PROPERTIES_PATH = "vm.author";

    /**
     * 默认author参数
     */
    private static final String AUTHOR_DEFAULT_PATH = "uhyils <247452312@qq.com>";

    /**
     * 数据库连接串
     */
    private String url;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 项目包名称
     */
    private String projectName;

    /**
     * 前台不传 项目名首字母大写
     */
    private String bigProjectName;

    /**
     * 前台不传 项目名首字母小写
     */
    private String smallProjectName;

    /**
     * 此项目端口
     */
    private Integer port;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 这次要导出的表
     */
    private List<String> tables;

    /**
     * 数据库连接
     */
    private Connection conn;

    /**
     * 作者
     */
    private String author;

    /**
     * 表信息
     */
    private Map<String, TableInfo> tableInfos;

    /**
     * 文件存储
     */
    private Map<String, String> fileMap;

    public DbInformation(DbInformationDTO dto) {
        this(dto.getUrl(), dto.getDbName(), dto.getProjectName(), dto.getBigProjectName(), dto.getSmallProjectName(), dto.getPort(), dto.getType(), dto.getUserName(), dto.getPassword(), dto
            .getTables(), dto.getAuthor());
    }

    public DbInformation(
        String url, String dbName, String projectName, String bigProjectName, String smallProjectName, Integer port, Integer type, String userName, String password, List<String> tables) {
        this.url = url;
        this.dbName = dbName;
        this.projectName = projectName;
        this.bigProjectName = bigProjectName;
        this.smallProjectName = smallProjectName;
        this.port = port;
        this.type = type;
        this.userName = userName;
        this.password = password;
        this.tables = tables;
    }

    public DbInformation(
        String url, String dbName, String projectName, String bigProjectName, String smallProjectName, Integer port, Integer type, String userName, String password, List<String> tables,
        String author) {
        this(url, dbName, projectName, bigProjectName, smallProjectName, port, type, userName, password, tables);
        this.author = author;
    }


    /**
     * 初始化连接
     *
     * @return
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection connect() {
        if (conn != null) {
            return conn;
        }
        Asserts.assertTrue(this.url != null);
        Asserts.assertTrue(this.userName != null);
        Asserts.assertTrue(this.password != null);
        try {
            switch (Objects.requireNonNull(DbTypeEnum.prase(this.type))) {
                case MYSQL:
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    break;
                case ORACLE:
                case SQLITE:
                default:
                    Asserts.throwException("暂时不支持数据库类型");
            }
            return this.conn = DriverManager.getConnection(this.url, this.userName, this.password);
        } catch (ClassNotFoundException | SQLException e) {
            LogUtil.error(this, e);
        }
        return null;
    }

    /**
     * 填充table信息
     */
    public void fillTableInfos() {
        fillDBInfo();
        fillDerivedInfo();
    }

    /**
     * 填充根据数据库信息衍生的信息
     */
    private void fillDerivedInfo() {
        for (Entry<String, TableInfo> entry : this.tableInfos.entrySet()) {
            TableInfo value = entry.getValue();
            TypeConvertor typeConvertor = TypeConvertorFactory.getTypeConvertor(DbTypeEnum.prase(type));
            for (Entry<String, ColumnInfo> columnInfoEntity : value.getColums().entrySet()) {
                ColumnInfo columnInfo = columnInfoEntity.getValue();
                String dataType = columnInfo.getDataType();
                String javaType = typeConvertor.databaseType2JavaType(dataType);
                columnInfo.setJavaType(javaType);
            }
        }
    }

    /**
     * 填充数据库相关信息
     */
    private void fillDBInfo() {
        Asserts.assertTrue(conn != null);
        DatabaseMetaData dmd;
        HashMap<String, TableInfo> stringTableInfoHashMap = new HashMap<>(16);
        try {
            /*获取数据库表们的信息*/
            dmd = conn.getMetaData();
            // 这里table可以使用正则,只要getTables允许
            for (String table : tables) {
                ResultSet rs = dmd.getTables(dbName, "%", table, new String[]{"TABLE"});
                while (rs.next()) {
                    String tableName = (String) rs.getObject("TABLE_NAME");
                    String tableComment = (String) rs.getObject("REMARKS");
                    if (stringTableInfoHashMap.containsKey(tableName)) {
                        continue;
                    }
                    /*获取table信息*/
                    TableInfo ti = new TableInfo(tableName, tableComment, new HashMap<>(16), new ColumnInfo());
                    stringTableInfoHashMap.put(tableName, ti);
                    ResultSet rs2 = dmd.getColumns(null, "%", tableName, "%");
                    while (rs2.next()) {
                        String typeName = rs2.getString("TYPE_NAME");
                        if (Objects.equals(typeName, "INT")) {
                            typeName = "INTEGER";
                        }
                        ColumnInfo ci = new ColumnInfo(typeName, 0, rs2.getString("COLUMN_NAME"));
                        String bigName = KproStringUtil.dealDbNameToJavaFileName(ci.getName());
                        String smallName = bigName.substring(0, 1).toLowerCase() + bigName.substring(1);
                        ci.setBigName(bigName);
                        ci.setSmallName(smallName);
                        ci.setRemark(rs2.getString("REMARKS"));
                        ti.getColums().put(rs2.getString("COLUMN_NAME"), ci);
                    }
                    rs2.close();

                    /*获取唯一字段信息*/
                    ResultSet rs3 = dmd.getPrimaryKeys(dbName, null, tableName);
                    while (rs3.next()) {
                        ColumnInfo ci2 = ti.getColums().get(rs3.getString("COLUMN_NAME"));
                        ci2.setKeyType(1);
                        ti.setOnlyKey(ci2);
                    }
                    rs3.close();
                }
                rs.close();
            }
        } catch (SQLException e) {
            LogUtil.error(this, e);
        } finally {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                LogUtil.error(this, e);
            }
        }
        this.tableInfos = stringTableInfoHashMap;
    }

    public DbTypeEnum getType() {
        return DbTypeEnum.prase(type);
    }

    /**
     * 结果
     *
     * @return
     */
    public Map<String, String> result() {
        return KproUtil.getMySqlKpro(this);
    }

    /**
     * 解析为模板类型代码
     *
     * @return 多个表的模板
     */
    public List<VelocityContext> parseToVelocityContext() {
        String packageProperties = SpringUtil.getProperty(PACKAGE_PROPERTIES_PATH, PACKAGE_DEFAULT_PATH);
        // 作者优先级 1.传入 2.配置文件 3.默认
        String authorProperties = author;
        if (StringUtil.isEmpty(authorProperties)) {
            authorProperties = SpringUtil.getProperty(AUTHOR_PROPERTIES_PATH, AUTHOR_DEFAULT_PATH);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String dateTime = sdf.format(new Date());
        List<VelocityContext> results = new ArrayList<>(tables.size());
        for (String tableName : tables) {
            VelocityContext velocityContext = new VelocityContext();
            TableInfo tableInfo = tableInfos.get(tableName);
            velocityContext.put("package", packageProperties);
            velocityContext.put("packagePath", packageProperties.replace(".", "/"));
            velocityContext.put("author", authorProperties);
            velocityContext.put("tableComment", tableInfo.getTableComment());
            velocityContext.put("dateTime", dateTime);
            velocityContext.put("tableName", tableName);
            velocityContext.put("className", tableInfo.getClassName());
            velocityContext.put("columns", tableInfo.getColums().values());
            velocityContext.put("pkColumn", tableInfo.getOnlyKey());
            results.add(velocityContext);
        }
        return results;


    }
}
