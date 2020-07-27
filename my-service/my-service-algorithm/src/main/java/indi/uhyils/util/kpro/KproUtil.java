package indi.uhyils.util.kpro;

import indi.uhyils.enum_.DbTypeEnum;
import indi.uhyils.pojo.request.DbInformation;
import indi.uhyils.pojo.tool.ColumnInfo;
import indi.uhyils.pojo.tool.TableInfo;
import indi.uhyils.util.LogUtil;

import java.sql.*;
import java.util.*;

/**
 * 项目生成模块功能实现
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月27日 07时19分
 */
public class KproUtil {
    /**
     * 生成mysql项目
     *
     * @param dbInformation 数据库信息
     * @return mysql生成的文件
     */
    public static HashMap<String, String> getMySqlKpro(DbInformation dbInformation) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = dbInformation.getUrl();
            String userName = dbInformation.getUserName();
            String password = dbInformation.getPassword();
            Connection conn = DriverManager.getConnection(url, userName, password);
            HashMap<String, TableInfo> tableInfos = getStringTableInfoHashMap(dbInformation, conn);
            conn.close();
            return getFileHashMapByTableInfos(tableInfos, dbInformation);


        } catch (Exception e) {
            LogUtil.error(KproUtil.class, e);
        }
        return new HashMap<>(0);
    }

    /**
     * 获取最终hashMap
     *
     * @param tableInfos    <table名称,table数据>
     * @param dbInformation
     * @return
     */
    private static HashMap<String, String> getFileHashMapByTableInfos(HashMap<String, TableInfo> tableInfos, DbInformation dbInformation) {
        HashMap<String, String> result = new HashMap<>();
        String apiPath = "my-api/my-api-provider-" + dbInformation.getDbName();
        for (Map.Entry<String, TableInfo> stringTableInfoEntry : tableInfos.entrySet()) {
            result.putAll(Objects.requireNonNull(createPojo(apiPath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), DbTypeEnum.prase(dbInformation.getType()))));
            result.putAll(Objects.requireNonNull(createDao(apiPath + "/src/main/java/indi/uhyils", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue(), DbTypeEnum.prase(dbInformation.getType()))));
            result.putAll(Objects.requireNonNull(createMapper(apiPath + "/src/main/resources/mapper/", KproStringUtil.dealDbNameToJavaFileName(stringTableInfoEntry.getKey()), stringTableInfoEntry.getValue())));
        }
        return result;
    }

    private static HashMap<String, String> createDao(String filepath, String className, TableInfo tableInfo, DbTypeEnum prase) {
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/dao/" + className + ".java";
        return null;
    }

    private static HashMap<String, String> createMapper(String filepath, String className, TableInfo tableInfo) {

        return null;
    }

    private static Map<String, String> createPojo(String filepath, String className, TableInfo tableInfo, DbTypeEnum typeEnum) {
        TypeConvertor typeConvertor = TypeConvertorFactory.getTypeConvertor(typeEnum);
        HashMap<String, String> result = new HashMap<>(1);
        String fileAllName = filepath + "/pojo/model/" + className + ".java";
        StringBuilder sb = new StringBuilder();
        List<JavaPojoFileGetAndSet> list = new ArrayList();

        //遍历字段,获取字段的类型,名称,是否主键
        Map<String, ColumnInfo> colums = tableInfo.getColums();
        for (Map.Entry<String, ColumnInfo> stringColumnInfoEntry : colums.entrySet()) {
            // 字段名称
            String filedName = stringColumnInfoEntry.getKey();
            ColumnInfo value = stringColumnInfoEntry.getValue();
            // 字段类型
            String dataType = value.getDataType();
            // 转换后的字段类型
            String type = typeConvertor.databaseType2JavaType(dataType);
            JavaPojoFileGetAndSet javaPojoFileGetAndSet = new JavaPojoFileGetAndSet(filedName, type);
            list.add(javaPojoFileGetAndSet);
        }
        //package
        sb.append("package indi.uhyils.pojo.model;\n\n");

        //import
        sb.append("import java.util.*;\n\n");
        sb.append("public class ");
        sb.append(className);
        sb.append("{\n\n");
        for (JavaPojoFileGetAndSet javaPojoFileGetAndSet : list) {
            String filedString = javaPojoFileGetAndSet.getFieldString();
            sb.append("\t");
            sb.append(filedString);
            sb.append("\n");
        }
        sb.append("\n");

        for (JavaPojoFileGetAndSet javaPojoFileGetAndSet : list) {
            String getMethod = javaPojoFileGetAndSet.getGetString();
            sb.append("\t");
            sb.append(getMethod);
            sb.append("\n");
        }
        sb.append("\n");

        for (JavaPojoFileGetAndSet javaPojoFileGetAndSet : list) {
            String setMethod = javaPojoFileGetAndSet.getSetString();
            sb.append("\t");
            sb.append(setMethod);
            sb.append("\n");
        }
        sb.append("\n}");
        result.put(fileAllName, sb.toString());
        return result;
    }

    /**
     * 获取oracle项目
     *
     * @param dbInformation 数据库信息
     * @return oracle生成的文件
     */
    public static HashMap<String, String> getOracleKpro(DbInformation dbInformation) {
        return null;
    }

    /**
     * 获取sqlite项目
     *
     * @param dbInformation 数据库信息
     * @return sqlite生成的文件
     */
    public static HashMap<String, String> getSqliteKpro(DbInformation dbInformation) {
        return null;
    }

    /**
     * 获取项目所有其他的东西
     *
     * @return
     */
    public static Map<String, String> getOtherKpro() {
        return new HashMap<>();
    }

    /**
     * 获取table们的信息
     *
     * @param dbInformation 数据库信息
     * @param conn          数据库连接
     * @return 需要的表信息
     * @throws SQLException
     */
    private static HashMap<String, TableInfo> getStringTableInfoHashMap(DbInformation dbInformation, Connection conn) throws SQLException {
        DatabaseMetaData dmd;
        HashMap<String, TableInfo> stringTableInfoHashMap = new HashMap<>();
        /*获取数据库表们的信息*/
        dmd = conn.getMetaData();
        // 这里table可以使用正则,只要getTables允许
        for (String table : dbInformation.getTables()) {
            ResultSet rs = dmd.getTables(dbInformation.getDbName(), "%", table, new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = (String) rs.getObject("TABLE_NAME");
                if (stringTableInfoHashMap.containsKey(tableName)) {
                    continue;
                }
                /*获取table信息*/
                TableInfo ti = new TableInfo(tableName, new HashMap<>(), new ColumnInfo());
                stringTableInfoHashMap.put(tableName, ti);
                ResultSet rs2 = dmd.getColumns(null, "%", tableName, "%");
                while (rs2.next()) {
                    ColumnInfo ci = new ColumnInfo(rs2.getString("TYPE_NAME"), 0, rs2.getString("COLUMN_NAME"));
                    ti.getColums().put(rs2.getString("COLUMN_NAME"), ci);
                }
                rs2.close();

                /*获取唯一字段信息*/
                ResultSet rs3 = dmd.getPrimaryKeys(dbInformation.getDbName(), null, tableName);
                while (rs3.next()) {
                    ColumnInfo ci2 = ti.getColums().get(rs3.getString("COLUMN_NAME"));
                    ci2.setKeyType(1);
                    ti.setOnlyKey(ci2);
                }
                rs3.close();
            }
            rs.close();

        }
        return stringTableInfoHashMap;
    }
}
