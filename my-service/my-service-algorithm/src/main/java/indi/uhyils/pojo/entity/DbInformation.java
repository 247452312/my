package indi.uhyils.pojo.entity;

import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.DTO.request.DbInformationDTO;
import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.pojo.tool.ColumnInfo;
import indi.uhyils.pojo.tool.TableInfo;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.kpro.KproStringUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月10日 15时01分
 */
public class DbInformation extends AbstractEntity {

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
     * 结果
     * key:文件相对路径
     * value:文件内容
     */
    private Map<String, String> kproResult;

    /**
     * 数据库连接
     */
    private Connection conn;

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
            .getTables());
    }

    public DbInformation(String url, String dbName, String projectName, String bigProjectName, String smallProjectName, Integer port, Integer type, String userName, String password, List<String> tables) {
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

    public void kpro() {
//        switch (Objects.requireNonNull(DbTypeEnum.prase(this.type))) {
//            case MYSQL:
//                this.kproResult = KproUtil.getMySqlKpro(this);
//                break;
//            case ORACLE:
//                this.kproResult = KproUtil.getOracleKpro(this);
//                break;
//            case SQLITE:
//                this.kproResult = KproUtil.getSqliteKpro(this);
//                break;
//            default:
//                AssertUtil.assertTrue(false, "暂时不支持数据库类型");
//        }
    }

    public Connection connect() throws ClassNotFoundException, SQLException {
        if (conn != null) {
            return conn;
        }
        AssertUtil.assertTrue(this.url != null);
        AssertUtil.assertTrue(this.userName != null);
        AssertUtil.assertTrue(this.password != null);
        switch (Objects.requireNonNull(DbTypeEnum.prase(this.type))) {
            case MYSQL:
                Class.forName("com.mysql.jdbc.Driver");
                break;
            case ORACLE:
            case SQLITE:
            default:
                AssertUtil.assertTrue(false, "暂时不支持数据库类型");
        }

        return this.conn = DriverManager.getConnection(this.url, this.userName, this.password);
    }

    public Map<String, String> result() {
        return kproResult;
    }

    public void saveToLocal(String path) {
        path = path.replace("\\", "/");
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        for (Entry<String, String> entry : kproResult.entrySet()) {
            String filePath = entry.getKey();
            String fileContent = entry.getValue();
            try {
                filePath = path + filePath;
                File file = new File(filePath);
                String dir = filePath.substring(0, filePath.lastIndexOf("/"));
                File dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(filePath);
                fw.write(fileContent);
                fw.close();
            } catch (IOException e) {
                LogUtil.error(this, e);
            }
        }

    }

    public void fillTableInfos() throws SQLException {
        AssertUtil.assertTrue(conn != null);
        DatabaseMetaData dmd;
        HashMap<String, TableInfo> stringTableInfoHashMap = new HashMap<>(16);
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
                    ColumnInfo ci = new ColumnInfo(rs2.getString("TYPE_NAME"), 0, rs2.getString("COLUMN_NAME"));
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
        conn.close();
        conn = null;
        this.tableInfos = stringTableInfoHashMap;
    }

    public void createFileMap(String now) {
//        HashMap<String, String> result = new HashMap<>(16);
//        String apiPath = "my-api/my-api-provider-" + smallProjectName;
//        String servicePath = "my-service/my-service-" + smallProjectName;
//        DbTypeEnum type = DbTypeEnum.prase(this.type);
//        for (Entry<String, TableInfo> entity : tableInfos.entrySet()) {
//            String className = KproStringUtil.dealDbNameToJavaFileName(entity.getKey());
//            result.putAll(Objects.requireNonNull(
//                KproUtil.createPojo(apiPath + "/src/main/java/indi/uhyils", className, entity.getValue(), now, type)));
//            result.putAll(Objects.requireNonNull(
//                KproUtil.createDao(servicePath + "/src/main/java/indi/uhyils", className, entity.getValue(), now, type)));
//            result.putAll(Objects.requireNonNull(
//                KproUtil.createMapper(servicePath + "/src/main/resources", className, entity.getValue(), now)));
//            result.putAll(Objects.requireNonNull(
//                KproUtil.createService(apiPath + "/src/main/java/indi/uhyils", className, entity.getValue(), now)));
//            result.putAll(Objects.requireNonNull(
//                KproUtil.createServiceImpl(servicePath + "/src/main/java/indi/uhyils", className, entity.getValue(), now)));
//            result.putAll(Objects.requireNonNull(
//                KproUtil.createHtml(servicePath + "/src/main/resources/static/page", className, entity.getValue(), now)));
//        }
//        result.putAll(Objects.requireNonNull(KproUtil.createOther(apiPath, servicePath, this, now, type)));
//
//        this.fileMap = result;
    }
}
